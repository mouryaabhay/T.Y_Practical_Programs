#include <stdio.h>
#include <stdlib.h>

#define MAX 200

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


void accept_requests(int requests[], int nreqs) {
    int i;
    printf("Enter disk requests:\n");
    for(i = 0; i < nreqs; i++) {
        printf("\nEnter your request %d : ",(i+1));
        scanf("%d", &requests[i]);
    }

    sort(requests, nreqs);
}


int c_look(int head, int direction, int requests[], int nreqs) {
    int i;
    int total_movement = 0;
    int index = 0;

    printf("\nHead Movement Order:\n");
    printf("%d ", head);


    for(i = 0; i < nreqs; i++)
        if(requests[i] >= head) {
            index = i;
            break;
        }


    if(direction == 1) {
        for(i = index; i < nreqs; i++) {
            total_movement += abs(head - requests[i]);
            head = requests[i];
            printf("-> %d ", head);
        }

        if(index > 0) {
            total_movement += abs(head - requests[0]);
            head = requests[0];
            printf("-> %d ", head);
        }

        for(i = 1; i < index; i++) {
            total_movement += abs(head - requests[i]);
            head = requests[i];
            printf("-> %d ", head);
        }
    } else {
        for(i = index - 1; i >= 0; i--) {
            total_movement += abs(head - requests[i]);
            head = requests[i];
            printf("-> %d ", head);
        }

        if(index < nreqs) {
            total_movement += abs(head - requests[nreqs - 1]);
            head = requests[nreqs - 1];
            printf("-> %d ", head);
        }

        for(i = nreqs - 2; i >= index; i--) {
            total_movement += abs(head - requests[i]);
            head = requests[i];
            printf("-> %d ", head);
        }
    }

    printf("\n\nTotal Seek Time = %d tracks \n", total_movement);
    return total_movement;
}

int main()
{
    int requests[200];
    int nreqs, head, direction;

    printf("Enter number of requests: ");
    scanf("%d", &nreqs);

    accept_requests(requests, nreqs);

    printf("Enter initial head position: ");
    scanf("%d", &head);

    printf("Enter direction of head movement:\n");
    printf("1 for RIGHT\n");
    printf("-1 for LEFT\n");
    printf("Enter your choice : ");
    scanf("%d", &direction);

    c_look(head, direction, requests, nreqs);
}
