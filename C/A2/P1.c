#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX 10   // Maximum number of memory blocks and files

/*
    Structure to store directory information of each file.
    This acts like a file table in an operating system.
*/
struct File_Details {
    char File_Name[10];     // Stores name of the file
    int File_start;         // Stores starting block number of the file
    int File_length;        // Stores number of blocks occupied by the file
} F1[MAX];                  // Directory table (array of files)

int Bit_vector[MAX];        // Bit vector to track free/allocated blocks
int count = 0;              // Number of files currently stored
int memory_size;            // Total number of memory blocks

// Initializes memory and bit vector
void initialize() {
    int i;

    printf("Enter total number of memory blocks (max %d): ", MAX);
    scanf("%d", &memory_size);

    // Restrict memory size to avoid array overflow
    if (memory_size > MAX) {
        printf("Memory size exceeds limit.\n");
        exit(0);
    }

    // Initially all memory blocks are free
    for (i = 0; i < memory_size; i++) {
        Bit_vector[i] = 1;   // 1 indicates free block
    }
}

// Displays current status of the bit vector
void Display_bit_vector() {
    int i;
    printf("\nBit Vector (1 = free, 0 = allocated):\n");
    for (i = 0; i < memory_size; i++) {
        printf("%d ", Bit_vector[i]);
    }
    printf("\n");
}

// Checks whether required number of contiguous blocks are free.
int Check_if_free_blocks_available(int start, int length) {
    int j;

    // Boundary check to avoid accessing memory beyond limit
    if (start + length > memory_size)
        return -1;

    // Check each block in the required range
    for (j = start; j < start + length; j++) {
        if (Bit_vector[j] == 0)
            return -1;   // Block already allocated
    }
    return 1;            // All blocks are free
}

// Updates the directory after successful allocation
void Update_directory(int start, int length, char *name) {
    strcpy(F1[count].File_Name, name); // Store file name in directory entry.
    F1[count].File_start = start; // Store starting block of the file.
    F1[count].File_length = length; // Store file length.
    count++; // Move to next directory entry.
}

// Creates a new file using First Fit contiguous allocation
void Create_File() {
    char name[10];
    int length, i, j, found = 0;

    // Check if directory is full
    if (count >= MAX) {
        printf("Directory full. Cannot create more files.\n");
        return;
    }

    printf("\nEnter File name: ");
    scanf("%9s", name);   // Limits input to avoid buffer overflow

    printf("Enter File length (in blocks): ");
    scanf("%d", &length);

    // First Fit strategy:
    // Scan memory from beginning and allocate first available contiguous block sequence.
    for (i = 0; i <= memory_size - length; i++) {
        if (Check_if_free_blocks_available(i, length) == 1) {
            // Mark all allocated blocks as 0 (occupied)
            for (j = i; j < i + length; j++) {
                Bit_vector[j] = 0;
            }

            // Update directory with file details
            Update_directory(i, length, name);

            printf("File '%s' created successfully.\n", name);
            found = 1;
            break;
        }
    }

    // If no contiguous space is found
    if (!found) {
        printf("Error: Not enough contiguous space.\n");
    }
}

// Displays directory table
void Display_directory() {
    int i;
    printf("\nDirectory:\n");
    printf("Name\tStart\tLength\n");

    // Directory shows mapping between file names
    // and their physical memory locations.
    for (i = 0; i < count; i++) {
        printf("%s\t%d\t%d\n", F1[i].File_Name, F1[i].File_start, F1[i].File_length);
    }
}

// Checks whether file exists in directory
int File_is_exist_or_not(char temp[]) {
    int i;
    for (i = 0; i < count; i++) {
        if (strcmp(F1[i].File_Name, temp) == 0)
            return i;   // Return index if file found
    }
    return -1;          // File not found
}

// Deletes a file and frees its allocated blocks
void Delete_File() {
    char name[10];
    int i, index;

    printf("\nEnter name to delete the file: ");
    scanf("%9s", name);

    index = File_is_exist_or_not(name);
    if (index == -1) {
        printf("Error: File not found.\n");
        return;
    }

        // Free memory blocks used by the file.
        // Uses starting block and length stored in directory.
    for (i = F1[index].File_start; i < F1[index].File_start + F1[index].File_length; i++) {
        Bit_vector[i] = 1;   // Mark block as free
    }

    /*
        Remove file entry from directory
        Shift remaining entries to fill the gap.
    */
    for (i = index; i < count - 1; i++) {
        F1[i] = F1[i + 1];
    }

    count--;  // Decrease file count

    printf("File '%s' deleted successfully.\n", name);
}

int main() {
    int choice;

    initialize();

    while (1) {
        printf("\n1. Show Bit Vector\n");
        printf("2. Create New File\n");
        printf("3. Show Directory\n");
        printf("4. Delete File\n");
        printf("5. Exit\n");
        printf("Enter choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1: Display_bit_vector(); break;
            case 2: Create_File(); break;
            case 3: Display_directory(); break;
            case 4: Delete_File(); break;
            case 5: exit(0);
            default: printf("Invalid choice\n");
        }
    }
    return 0;
}
