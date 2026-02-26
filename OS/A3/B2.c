#include <stdio.h>
#include <stdlib.h>

#define MAX_CYL 200

// Function to sort array using Bubble Sort
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

// Function to accept disk requests
void accept_requests(int requests[], int nreqs) {
    int i;
    printf("Enter disk requests:\n");
    for(i = 0; i < nreqs; i++) {
        printf("\nEnter your request %d : ",(i+1));
        scanf("%d", &requests[i]);
    }

    sort(requests, nreqs);  // Sort requests
}

// C-SCAN Algorithm
int c_scan(int head, int direction, int requests[], int nreqs) {
    int i;
    int total_movement = 0;
    int index = 0;

    printf("\nHead Movement Order:\n");
    printf("%d ", head);

    // Find first request greater than or equal to head
    for(i = 0; i < nreqs; i++) {
        if(requests[i] >= head) {
            index = i;
            break;
        }
    }

    // If moving RIGHT
    if(direction == 1) {
        // Move right
        for(i = index; i < nreqs; i++) {
            total_movement += abs(head - requests[i]);
            head = requests[i];
            printf("-> %d ", head);
        }

        // Go to end of disk
        total_movement += abs(head - (MAX_CYL - 1));
        head = MAX_CYL - 1;
        printf("-> %d ", head);

        // Jump to beginning (circular move)
        total_movement += abs(head - 0);
        head = 0;
        printf("-> %d ", head);

        // Serve remaining requests
        for(i = 0; i < index; i++) {
            total_movement += abs(head - requests[i]);
            head = requests[i];
            printf("-> %d ", head);
        }
    } else { // If moving LEFT
        // Move left
        for(i = index - 1; i >= 0; i--) {
            total_movement += abs(head - requests[i]);
            head = requests[i];
            printf("-> %d ", head);
        }

        // Go to beginning
        total_movement += abs(head - 0);
        head = 0;
        printf("-> %d ", head);

        // Jump to end (circular move)
        total_movement += abs(head - (MAX_CYL - 1));
        head = MAX_CYL - 1;
        printf("-> %d ", head);

        // Serve remaining requests
        for(i = nreqs - 1; i >= index; i--) {
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

    // Ask direction before execution
    printf("Enter direction of head movement:\n");
    printf("1 for RIGHT\n");
    printf("-1 for LEFT\n");
    printf("Enter your choice : ");
    scanf("%d", &direction);

    c_scan(head, direction, requests, nreqs);

    return 0;
}
