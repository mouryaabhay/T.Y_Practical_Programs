#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Structure to store details of each file in the directory
struct file_details {
    char file_name[10];   // Name of the file (up to 9 characters + null terminator)
    int file_size;        // Size of the file (in memory blocks)
    int file_start;       // Starting memory block where the file is allocated
} directory[100];         // Array to store up to 100 files

int bit_vector[100];      // Array representing memory blocks: 1 = free, 0 = allocated
int memory_size;          // Total number of memory blocks
int count = 0;            // Number of files currently in the directory

// Initializes the bit vector and sets all memory blocks as free
void initialize_bit_vector() {
    int i;
    printf("Enter total number of memory blocks: ");
    scanf("%d", &memory_size);

    for (i = 0; i < memory_size; i++)
        bit_vector[i] = 1;   // 1 means block is free
}

// Displays the status of all memory blocks (free or allocated)
void show_memory_blocks() {
    int i;
    printf("\nMemory Blocks:\n");
    for (i = 0; i < memory_size; i++)
        printf("%d ", bit_vector[i]);
    printf("\n");
}

// Checks if a specific memory block is free
int check_if_freeblock(int block_no) {
    if (bit_vector[block_no] == 1)
        return 1;  // Block is free
    else
        return 0;  // Block is allocated
}

// Finds a contiguous sequence of free memory blocks large enough for a file
// Returns the starting index if found, otherwise -1
int check_for_continuous_space(int file_size) {
    int i, j, flag = 0;
    for (i = 0; i <= memory_size - file_size; i++) {
        flag = 0; // Reset flag for each new starting position
        for (j = i; j < i+file_size; j++) {
            if (!check_if_freeblock(j)) {
                flag = 1; // Found an allocated block, break
                break;
            }
        }
        if (!flag)
            return i; // Found a suitable starting position
    }
    return -1; // No suitable space found
}

// Marks a range of memory blocks as allocated (sets them to 0)
void update_bit_vector(int start_pos, int file_size) {
    int i;
    for (i = start_pos; i < start_pos + file_size; i++)
        bit_vector[i] = 0;
}

// Adds a new file entry to the directory
void add_directory_entry(char *fileName, int filesize, int start_pos) {
    strcpy(directory[count].file_name, fileName);
    directory[count].file_size = filesize;
    directory[count].file_start = start_pos;
    count++;
}

// Handles the creation of a new file: prompts for details, checks space, updates memory and directory
void create_file() {
    char filename[10];
    int filesize, start_pos;

    printf("Enter file name: ");
    scanf("%s", filename);

    printf("Enter file size (blocks): ");
    scanf("%d", &filesize);

    // Check for available contiguous space
    start_pos = check_for_continuous_space(filesize);

    if (start_pos == -1) {
        printf("Enough contiguous memory is not available for file creation\n");
    }
    else {
        update_bit_vector(start_pos, filesize);           // Mark blocks as allocated
        add_directory_entry(filename, filesize, start_pos); // Add file to directory
        printf("File created successfully\n");
    }
}

// Handles deletion of a file: prompts for file name, frees memory, removes from directory
void delete_file() {
    int i, j, fileSize, filestart;
    char filename[10];

    printf("Enter file name to delete: ");
    scanf("%s", filename);

    // Search for the file in the directory
    for (i = 0; i < count; i++) {
        if (strcmp(directory[i].file_name, filename) == 0) {
            fileSize = directory[i].file_size;
            filestart = directory[i].file_start;

            // Free the memory blocks occupied by the file
            for (j = filestart; j < filestart + fileSize; j++)
                bit_vector[j] = 1;

            // Remove the file from the directory by shifting entries
            for (j = i; j < count - 1; j++)
                directory[j] = directory[j + 1];

            count--;
            printf("File has been deleted successfully\n");
            return;
        }
    }
    // File not found in directory
    printf("%s not found\n",filename);
}

// Displays all files currently in the directory with their details
void display_directory() {
    int i;
    printf("\nContents of the directory\n");
    printf("File Name\tStart Block\tSize\n");

    for (i = 0; i < count; i++) {
        printf("%s\t\t%d\t\t%d\n",
               directory[i].file_name,
               directory[i].file_start,
               directory[i].file_size);
    }
}

// Main function: provides a menu-driven interface for file operations
int main() {
    int choice;
    initialize_bit_vector(); // Set up memory blocks
    do {
        printf("\nMenu driven program\n");
        printf("1. Create File\n");
        printf("2. Delete File\n");
        printf("3. Display Directory\n");
        printf("4. Show Memory Blocks\n");
        printf("5. Exit\n");
        printf("Enter choice: ");
        scanf("%d", &choice);
        switch (choice) {
            case 1: create_file(); // Create a new file
                    break;
            case 2: delete_file(); // Delete an existing file
                    break;
            case 3: display_directory(); // Show all files in directory
                    break;
            case 4: show_memory_blocks(); // Show memory block status
                    break;
            case 5: exit(0); // Exit the program
            default: printf("Invalid choice.\n");
        }
    } while (choice != 5);
    return 0;
}