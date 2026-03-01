// Write an OS program to implement SCAN Disk Scheduling algorithm.

#include <stdio.h>
#include <stdlib.h>

#define MAX_CYL 200

// Function to sort requests in ascending order (Bubble Sort)
void sort(int arr[], int n) {
    int i, j, temp;
    for(i = 0; i < n-1; i++)
        for(j = 0; j < n-i-1; j++)
            if(arr[j] > arr[j+1]) {
                temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
}

// Function to accept disk requests 176, 79, 34, 60, 92, 11, 41, 114
void accept_requests(int requests[], int nreqs) {
    int i;
    printf("Enter the disk requests:\n");
    for(i = 0; i < nreqs; i++) {
        printf("\nRequest %d : ",(i+1));
        scanf("%d", &requests[i]);
    }

    sort(requests, nreqs);   // Sort requests
}

// SCAN algorithm
int scan(int head, int direction, int requests[], int nreqs) {
    int i;
    int total_movement = 0;
    int index;

    printf("\nHead Movement Order:\n");
    printf("%d ", head);

    // Find index of first request greater than head
    for(i = 0; i < nreqs; i++) {
        if(requests[i] >= head) {
            index = i;
            break;
        }
    }

    // If direction is RIGHT
    if (direction == 1) {
        // Move right
        for(i = index; i < nreqs; i++) {
            total_movement += abs(head - requests[i]);
            head = requests[i];
            printf("-> %d ", head);
        }

        // Go to end
        total_movement += abs(head - (MAX_CYL - 1));
        head = MAX_CYL - 1;
        printf("-> %d ", head);

        // Move left
        for(i = index - 1; i >= 0; i--) {
            total_movement += abs(head - requests[i]);
            head = requests[i];
            printf("-> %d ", head);
        }
    } else { // If direction is LEFT
        // Move left
        for(i = index - 1; i >= 0; i--) {
            total_movement += abs(head - requests[i]);
            head = requests[i];
            printf("-> %d ", head);
        }

        // Go to start
        total_movement += abs(head - 0);
        head = 0;
        printf("-> %d ", head);

        // Move right
        for(i = index; i < nreqs; i++) {
            total_movement += abs(head - requests[i]);
            head = requests[i];
            printf("-> %d ", head);
        }
    }

    printf("\n\nTotal Head Movement = %d\n", total_movement);
    return total_movement;
}

int main() {
    int requests[200];
    int nreqs, head, direction;

    printf("Enter number of requests: ");
    scanf("%d", &nreqs);

    accept_requests(requests, nreqs);

    printf("Enter initial head position: ");
    scanf("%d", &head);

    // Asking direction BEFORE execution
    printf("Enter direction of movement:\n");
    printf("1 for RIGHT\n");
    printf("-1 for LEFT\n");
    printf("Enter your choice : ");
    scanf("%d", &direction);

    scan(head, direction, requests, nreqs);

    return 0;
}
