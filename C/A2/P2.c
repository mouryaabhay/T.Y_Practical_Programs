#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

struct File_Details {
    char File_Name[10];
    int File_start;
    int File_length;
    int File_end;
} F1[10];

int Bit_vector[10], count = 0;
int memory_size, free_blocks;

void initial() {
    int i;
    printf("Enter total number of memory blocks: ");
    scanf("%d", &memory_size);
    free_blocks = memory_size;
    for (i = 0; i < memory_size; i++) {
        Bit_vector[i] = -1; // -1 = free
    }
}

void Display_bit_vector() {
    int i;
    printf("\nBit Vector (-1=free, other=link):\n");
    for (i = 0; i < memory_size; i++) {
        printf("%d ", Bit_vector[i]);
    }
    printf("\nFree blocks: %d\n", free_blocks);
}

int Check_if_free_blocks_available(int needed) {
    if (free_blocks >= needed) return 1;
    return -1;
}

void Update_directory(int start, int length, int end, char *name) {
    strcpy(F1[count].File_Name, name);
    F1[count].File_start = start;
    F1[count].File_length = length;
    F1[count].File_end = end;
    count++;
}

void Create_File() {
    char name[10];
    int length, i, blocks_allocated = 0, prev = -1, start = -1, rand_block;

    printf("\nEnter File name: ");
    scanf("%s", name);
    printf("Enter File length (in blocks): ");
    scanf("%d", &length);

    if (Check_if_free_blocks_available(length) == -1) {
        printf("Error: Not enough free blocks.\n");
        return;
    }

    srand(time(0));
    while (blocks_allocated < length) {
        rand_block = rand() % memory_size;
        if (Bit_vector[rand_block] == -1) {
            if (blocks_allocated == 0) {
                start = rand_block;
            } else {
                Bit_vector[prev] = rand_block;
            }
            Bit_vector[rand_block] = -9;
            prev = rand_block;
            blocks_allocated++;
            free_blocks--;
        }
    }
    Bit_vector[prev] = -9;

    Update_directory(start, length, prev, name);
    printf("File '%s' created successfully.\n", name);
}

void Display_directory() {
    int i;
    printf("\nDirectory:\n");
    printf("Name\tStart\tEnd\tLength\n");
    for (i = 0; i < count; i++) {
        printf("%s\t%d\t%d\t%d\n", F1[i].File_Name, F1[i].File_start, F1[i].File_end, F1[i].File_length);
    }
}

int File_is_exist_or_not(char temp[]) {
    int i;
    for (i = 0; i < count; i++) {
        if (strcmp(F1[i].File_Name, temp) == 0) {
            return i;
        }
    }
    return -1;
}

void Delete_File() {
    char name[10];
    int i, index, current, next;

    printf("\nEnter name to Delete the file: ");
    scanf("%s", name);

    index = File_is_exist_or_not(name);
    if (index == -1) {
        printf("Error: File not found.\n");
        return;
    }

    current = F1[index].File_start;
    while (current != -9) {
        next = Bit_vector[current];
        Bit_vector[current] = -1;
        free_blocks++;
        current = next;
    }

    for (i = index; i < count - 1; i++) {
        F1[i] = F1[i + 1];
    }
    count--;

    printf("File '%s' deleted successfully.\n", name);
}

int main() {
    int choice;
    initial();

    while (1) {
        printf("\n1. Show Bit vector\n");
        printf("2. Create New File\n");
        printf("3. Show Directory\n");
        printf("4. Delete File\n");
        printf("5. Exit\n");
        printf("\nEnter choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1: Display_bit_vector(); break;
            case 2: Create_File(); break;
            case 3: Display_directory(); break;
            case 4: Delete_File(); break;
            case 5: exit(0); break;
            default: printf("\nInvalid Choice\n");
        }
    }
    return 0;
}