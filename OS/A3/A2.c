#include <stdio.h>
#include <stdlib.h>

void accept_requests(int requests[], int nreqs) {
    int i;
    printf("Enter the disk requests:\n");
    for(i = 0; i < nreqs; i++) {
        printf("Request %d: ", i + 1);
        scanf("%d", &requests[i]);
    }
}

int sstf(int head, int requests[], int nreqs) {
    int visited[200] = {0}; // Marks serviced requests
    int total_movement = 0;
    int current_head = head;
    int completed = 0;

    printf("\nSSTF DISK SCHEDULING ALGORITHM\n");
    printf("\nSequence of head movement:\n");
    printf("%d", current_head);

    while(completed < nreqs) {
        int min_dist = 999999, nearest_index = -1, i;

        // Find the closest unvisited request
        for(i = 0; i < nreqs; i++) {
            if(!visited[i]) {
                int dist = abs(current_head - requests[i]);

                // Tie-breaking rule: If two tracks are equally close, choose the smaller track number
                if(dist < min_dist || (dist == min_dist && requests[i] < requests[nearest_index])) {
                    min_dist = dist;
                    nearest_index = i;
                }
            }
        }

        // Move head to nearest request
        current_head = requests[nearest_index];
        visited[nearest_index] = 1;
        total_movement += min_dist;
        completed++;

        printf(" -> %d", current_head);
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
