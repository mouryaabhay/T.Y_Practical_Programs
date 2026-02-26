// Write an OS program to implement LOOK Disk Scheduling algorithm.

#include <stdio.h>
#include <stdlib.h>
#define MAX 200

// Bubble Sort
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

// Accept disk requests
void accept_requests(int requests[], int nreqs) {
    int i;
    printf("Enter disk requests:\n");
    for(i = 0; i < nreqs; i++) {
        printf("\nEnter your request %d : ",(i+1));
        scanf("%d", &requests[i]);
    }

    sort(requests, nreqs);
}

// LOOK Algorithm
int look(int head, int direction, int requests[], int nreqs) {
    int i;
    int total_movement = 0;
    int index = 0;

    printf("\nHead Movement Order:\n");
    printf("%d ", head);

    // Find first request greater than or equal to head
    for(i = 0; i < nreqs; i++)
        if(requests[i] >= head) {
            index = i;
            break;
        }

    // If direction is RIGHT
    if(direction == 1) {
        // Move right
        for(i = index; i < nreqs; i++) {
            total_movement += abs(head - requests[i]);
            head = requests[i];
            printf("-> %d ", head);
        }

        // Reverse direction (no going to MAX)
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

        // Reverse direction (no going to 0)
        for(i = index; i < nreqs; i++) {
            total_movement += abs(head - requests[i]);
            head = requests[i];
            printf("-> %d ", head);
        }
    }

    printf("\n\nTotal Seek Time = %d tracks\n", total_movement);
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

    // Ask direction before execution
    printf("Enter direction of head movement:\n");
    printf("1 for RIGHT\n");
    printf("-1 for LEFT\n");
    printf("Enter your choice : ");
    scanf("%d", &direction);

    look(head, direction, requests, nreqs);

    return 0;
}
