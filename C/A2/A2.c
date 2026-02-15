// Write a program to simulate Linked file allocation method. Assume disk with n
// number of blocks. Give value of n as input. Write menu driver program with menu
// options as mentioned above and implement each option.

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

struct file_details {
    char file_name[10];
    int file_size;
    int file_start;
    int file_end;
} directory[100];

int bit_vector[100]; // -1 = free block, otherwise stores next block index
int memory_size, free_blocks, count = 0;

// Initialize memory
void initialize_bit_vector() {
    int i;
    printf("Enter total number of memory blocks: ");
    scanf("%d", &memory_size);

    for (i = 0; i < memory_size; i++)
        bit_vector[i] = -1;   // all blocks free

    free_blocks = memory_size;
}

// Show memory status
void show_memory_blocks() {
    int i;
    printf("\nMemory Blocks:\n");
    for (i = 0; i < memory_size; i++)
        printf("%d ", bit_vector[i]);

    printf("\nFree blocks: %d\n", free_blocks);
}

// Update a block
void update_bit_vector(int start_pos, int value) {
    bit_vector[start_pos] = value;
}

// Add file entry
void add_directory_entry(char *filename, int filesize, int start_pos, int end_pos) {
    strcpy(directory[count].file_name, filename);
    directory[count].file_size = filesize;
    directory[count].file_start = start_pos;
    directory[count].file_end = end_pos;
    count++;
}

// Create file (Linked Allocation)
void create_file() {
    int i, filesize, start_pos = -1, prev_pos = -1, curr_pos;
    char filename[10];

    printf("Enter file name: ");
    scanf("%s", filename);

    for(i = 0; i < count; i++) {
        if(strcmp(directory[i].file_name, filename) == 0) {
            printf("\nFile already exists!\n");
            return;
        }
    }

    printf("Enter file size (blocks): ");
    scanf("%d", &filesize);

    if (filesize > free_blocks) {
        printf("Enough memory not available\n");
        return;
    }

    i = 0;
    while (i != filesize) {
        curr_pos = rand() % memory_size;

        if(bit_vector[curr_pos] == -1) {
            if(start_pos == -1)
                start_pos = curr_pos; //first block of file
            else
                update_bit_vector(prev_pos, curr_pos);  //link previous block to current block

            update_bit_vector(curr_pos, -9);
            prev_pos = curr_pos;
            i++; // one block is allocated in memory
        }
    }

    add_directory_entry(filename, filesize, start_pos, prev_pos);

    free_blocks -= filesize;

    printf("File created successfully.\n");
    printf("Start block: %d  End block: %d\n", start_pos, prev_pos);
}

// Delete file
void delete_file() {
    int i, j, start_pos, next_pos, temp;
    char filename[10];

    printf("Enter file name to delete: ");
    scanf("%s", filename);

    for (i = 0; i < count; i++) {
        if (strcmp(directory[i].file_name, filename) == 0) {
            start_pos = directory[i].file_start;
            next_pos = start_pos;

            while (next_pos != -9) {
                temp = bit_vector[next_pos];
                bit_vector[next_pos] = -1;
                next_pos = temp;
            }

            free_blocks += directory[i].file_size;

            for (j = i; j < count - 1; j++)
                directory[j] = directory[j + 1];

            count--;
            printf("File deleted successfully\n");
            return;
        }
    }
    printf("File not found\n");
}

// Display directory
void display_directory() {
    int i;
    printf("\nFile Directory:\n");
    printf("Name\tSize\tStart\tEnd\n");

    for (i = 0; i < count; i++)
        printf("%s\t%d\t%d\t%d\n",
            directory[i].file_name,
            directory[i].file_size,
            directory[i].file_start,
            directory[i].file_end);
}

// Main function
int main() {
    int choice;

    srand(time(NULL));
    initialize_bit_vector();

    do {
        printf("\n1. Create File");
        printf("\n2. Delete File");
        printf("\n3. Display Directory");
        printf("\n4. Show Memory Blocks");
        printf("\n5. Exit");
        printf("\nEnter choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1: create_file(); break;
            case 2: delete_file(); break;
            case 3: display_directory(); break;
            case 4: show_memory_blocks(); break;
            case 5: break;
            default: printf("Invalid choice\n");
        }
    } while (choice != 5);

    return 0;
}
