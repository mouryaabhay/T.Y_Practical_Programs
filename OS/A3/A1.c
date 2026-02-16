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

int fcfs(int head, int requests[], int nreqs) {
    int i, total_movement = 0;
    int current_head = head;

    printf("\nSequence of head movement:\n");
    printf("%d", head);

    for(i = 0; i < nreqs; i++) {
        // Calculate Total Head movement
        total_movement += abs(current_head - requests[i]);
        current_head = requests[i];
        printf(" -> %d", current_head);
    }

    printf("\n\nTotal Head Movement: %d tracks\n", total_movement);
    return total_movement;
}

int main() {
    int requests[200];
    int nreqs, head;

    printf("FCFS DISK SCHEDULING ALGORITHM\n");

    // Accept Number of requests
    printf("Enter the number of requests: ");
    scanf("%d", &nreqs);

    accept_requests(requests, nreqs);

    // Accept Initial head position
    printf("Enter the initial head position: ");
    scanf("%d", &head);

    fcfs(head, requests, nreqs);

    return 0;
}