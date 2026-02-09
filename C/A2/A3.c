#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_FILES 1000
#define MAX_BLOCKS 1000

typedef struct {
    char *filename;     // Name of the file
    int file_size;      // Size of the file (in blocks)
    int index_block;    // Index block number
} file_detail;

typedef struct {
    int allocated;                  // 1 = allocated, 0 = free
    int index[MAX_BLOCKS - 1];      // Stores data block numbers
} block;

block bit_vector[MAX_BLOCKS];
file_detail *dir[MAX_FILES];

int memory_size = 0;
int free_memory = 0;

/* Initialize memory and directory */
void initialize_bit_vector() {
    int i;
    printf("Enter total number of memory blocks: ");
    scanf("%d", &memory_size);

    free_memory = memory_size;

    for (i = 0; i < memory_size; i++)
        bit_vector[i].allocated = 0;

    for (i = 0; i < MAX_FILES; i++)
        dir[i] = NULL;
}

/* Display memory allocation status */
void show_memory_blocks() {
    printf("\nMemory Blocks Status:\n");

    for (int i = 0; i < memory_size; i++)
        printf("Block %d : %d\n", i, bit_vector[i].allocated);
}

/* Add file to directory */
void update_directory(file_detail *file) {
    int i;
    for (i = 0; i < MAX_FILES; i++) {
        if (dir[i] == NULL) {
            dir[i] = file;
            return;
        }
    }
}

/* Search file in directory */
int get_file(char *filename) {
    int i;
    for (i = 0; i < MAX_FILES; i++) {
        if (dir[i] != NULL && strcmp(dir[i]->filename, filename) == 0) {
            return i;
        }
    }
    return -1;
}

/* Create a new file */
void create_file() {
    char filename[256];
    file_detail *file;
    int file_size, i, id;

    printf("Enter filename: ");
    scanf("%s", filename);

    if (get_file(filename) != -1) {
        printf("Error: File already exists!\n");
        return;
    }

    printf("Enter file size (in blocks): ");
    scanf("%d", &file_size);

    if (file_size + 1 > free_memory) {
        printf("Error: Not enough memory!\n");
        return;
    }

    file = (file_detail *)malloc(sizeof(file_detail));
    file->filename = (char *)malloc(strlen(filename) + 1);
    strcpy(file->filename, filename);
    file->file_size = file_size;
    file->index_block = -1;

    for (i = 0; i < file_size + 1; i++) {
        while(1) {
            id = rand() % memory_size;

            if(bit_vector[id].allocated == 0)
                break;
        }

        if (file->index_block == -1) {
            bit_vector[id].allocated = 1;
            file->index_block = id;
        } else{
            bit_vector[file->index_block].index[i - 1] = id;
            bit_vector[id].allocated = 1;
        }
        free_memory--;
    }

    update_directory(file);
    printf("File created successfully\n");
}

/* Delete a file */
void delete_file() {
    char filename[256];
    int idx, i, id;
    file_detail *file;

    printf("Enter filename to delete: ");
    scanf("%s", filename);

    idx = get_file(filename);
    if (idx == -1) {
        printf("Error: File not found!\n");
        return;
    }

    file = dir[idx];

    for (i = 0; i < file->file_size; i++) {
        id = bit_vector[file->index_block].index[i];
        bit_vector[id].allocated = 0;
    }

    bit_vector[file->index_block].allocated = 0;
    free_memory += file->file_size + 1;

    free(file->filename);
    free(file);
    dir[idx] = NULL;

    printf("File deleted successfully.\n");
}

/* Display directory */
void display_dir() {
    int i, j;
    file_detail *file;

    printf("\nDirectory Contents:\n");
    printf("Name\tSize\tIndex\tBlocks\n");

    for (i = 0; i < MAX_FILES; i++) {
        if (dir[i] == NULL)
            continue;

        file = dir[i];
        printf("%4s\t%d\t%d\t", file->filename, file->file_size, file->index_block);

        for (j = 0; j < file->file_size; j++)
            printf("%d ", bit_vector[file->index_block].index[j]);

        printf("\n");
    }
}

/* Main menu */
int main() {
    int choice;

    initialize_bit_vector();

    while (1) {
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
            case 3: display_dir(); break;
            case 4: show_memory_blocks(); break;
            case 5: exit(0); break;
            default: printf("Invalid choice!\n");
        }
    }
}