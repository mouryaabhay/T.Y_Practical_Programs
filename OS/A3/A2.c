// Write an OS program to implement SSTF Disk Scheduling algorithm.

#include <stdio.h>
#include <stdlib.h>

// Bubble Sort
void sort(int arr[], int nreqs) {
    int i, j, temp;
    for(i = 0; i < nreqs - 1; i++)
        for(j = 0; j < nreqs - i - 1; j++)
            if(arr[j] > arr[j + 1]) {
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
}

// Accept disk requests
void accept_requests(int requests[], int nreqs) {
    int i;
    printf("Enter the disk requests:\n");
    for(i = 0; i < nreqs; i++) {
        printf("Request %d: ", i + 1);
        scanf("%d", &requests[i]);
    }
    sort(requests, nreqs);   // Sorting
}

// SSTF Algorithm
int sstf(int head, int requests[], int nreqs, int tie_direction) {
    int visited[200];
    int total_movement = 0;
    int count = nreqs;
    int i;

    for(i = 0;i < nreqs;i++)
        visited[i] = 0;

    printf("\nHead Movement Order:\n");
    printf("%d ", head);

    while(count > 0) {
        int min_dist = 9999;
        int index = -1;

        for(i = 0; i < nreqs; i++) {
            if(!visited[i]) {
                int dist = abs(head - requests[i]);

                if(dist < min_dist) {
                    min_dist = dist;
                    index = i;
                } else if(dist == min_dist) {
                    // If tie occurs, follow user preference
                    if(tie_direction == -1 && requests[i] < head)
                        index = i;
                    else if(tie_direction == 1 && requests[i] > head)
                        index = i;
                }
            }
        }

        total_movement += abs(head - requests[index]);
        head = requests[index];
        visited[index] = 1;

        printf("-> %d ", head);
        count--;
    }

    printf("\n\nTotal Head Movement: %d tracks\n", total_movement);
    return total_movement;
}

int main() {
    int requests[200];
    int nreqs, head;
    int tie_direction;

    // Accept Number of requests
    printf("Enter the number of requests: ");
    scanf("%d", &nreqs);

    accept_requests(requests, nreqs);

    // Accept Initial head position
    printf("Enter the initial head position: ");
    scanf("%d", &head);

    printf("If equal distance occurs:\n");
    printf("Enter 1 to go RIGHT\n");
    printf("Enter -1 to go LEFT\n");
    scanf("%d", &tie_direction);

    sstf(head, requests, nreqs, tie_direction);

    return 0;
}
