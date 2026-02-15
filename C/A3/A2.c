#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

void accept_requests(int requests[], int nreqs) {
    int i;
    printf("Enter the disk requests:\n");
    for(i = 0; i < nreqs; i++) {
        printf("Request %d: ", i+1);
        scanf("%d", &requests[i]);
    }
}

void sort(int arr[], int n) {
    int i, j, temp;
    // Bubble sort to sort requests in ascending order
    for(i = 0; i < n-1; i++) {
        for(j = 0; j < n-i-1; j++) {
            if(arr[j] > arr[j+1]) {
                temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
        }
    }
}

int sstf(int head, int requests[], int nreqs) {
    int i, total_movement = 0;
    int visited[200] = {0};
    int count = nreqs;
    int current_head = head;
    int nearest, min_dist;
    int nearest_index;

    // Sort the requests first
    sort(requests, nreqs);

    printf("\nSequence of head movement:\n");
    printf("%d", head);

    while(count > 0) {
        min_dist = INT_MAX;
        nearest_index = -1;

        // Find the nearest unvisited request
        for(i = 0; i < nreqs; i++) {
            if(!visited[i]) {
                int dist = abs(current_head - requests[i]);
                if(dist < min_dist)
                {
                    min_dist = dist;
                    nearest_index = i;
                    nearest = requests[i];
                }
            }
        }

        if(nearest_index != -1) {
            // Update total movement
            total_movement += min_dist;
            current_head = nearest;
            visited[nearest_index] = 1;
            count--;
            printf(" -> %d", current_head);
        }
    }

    printf("\n\nTotal Head Movement: %d tracks\n", total_movement);
    return total_movement;
}

int main() {
    int requests[200];
    int nreqs, head;

    printf("SSTF DISK SCHEDULING ALGORITHM\n");

    // Accept Number of requests
    printf("Enter the number of requests: ");
    scanf("%d", &nreqs);

    accept_requests(requests, nreqs);

    // Accept Initial head position
    printf("Enter the initial head position: ");
    scanf("%d", &head);

    sstf(head, requests, nreqs);

    return 0;
}