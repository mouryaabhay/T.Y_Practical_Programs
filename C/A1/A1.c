/*
 * Add the following functionalities in your program
 * a) Accept Available
 * b) Display Allocation, Max
 * c) Display the contents of need matrix
 * d) Display Available
 *
 * Process | Allocation | MAX
 *         | A B C      | A B C
 * P0      | 0 1 0      | 7 5 3
 * P1      | 2 0 0      | 3 2 2
 * P2      | 3 0 2      | 9 0 2
 * P3      | 2 1 1      | 2 2 2
 * P4      | 0 0 2      | 4 3 3
 */

#include <stdio.h>
#include <stdlib.h>

int avail[10];
int max[10][10], alloc[10][10], need[10][10], request[10];
int finish[10] = {0}, work[10], safe[10] = {-1};
int m, n, proc;   // proc = process Pi for request

int compare_need(int p) {
    int j;

    for (j = 0; j < m; j++)
        if (need[p][j] > work[j])
            return -1;
    return p;
}

void accept_matrix(int A[][10]) {
    int i, j;

    for (i = 0; i < n; i++) {
        printf("\nFor process %d : ", i);
        for (j = 0; j < m; j++)
            scanf("%d", &A[i][j]);
    }
}

void accept_available() {
    int i;
    printf("\nEnter the available matrix : ");
    for (i = 0; i < m; i++)
        scanf("%d", &avail[i]);
}

void calculate_need() {
    int i, j;

    for (i = 0; i < n; i++)
        for (j = 0; j < m; j++)
            need[i][j] = max[i][j] - alloc[i][j];
}

void safety_algo() {
    int i, k, l = 0, flag, pno;
    int over = 0;

    for (i = 0; i < m; i++)
        work[i] = avail[i];

    while (!over) {
        flag = 0;

        for (i = 0; i < n; i++)
            if (finish[i] == 0 && compare_need(i) != -1) {
                pno = i;
                flag = 1;
                break;
            }

        if (!flag) {
            printf("System is unsafe\n");
            exit(1);
        }

        for (k = 0; k < m; k++)
            work[k] += alloc[pno][k];

        finish[pno] = 1;
        safe[l++] = pno;

        if (l == n) {
            printf("\nSafe sequence is : ");
            for (i = 0; i < n; i++)
                printf("P%d\t", safe[i]);
            over = 1;
            printf("\n");
        }
    }
}

void resource_request_algo() {
    int i;

    for (i = 0; i < m; i++) {
        if (request[i] > need[proc][i]) {
            printf("Error: process exceeds its maximum demand\n");
            exit(1);
        }
    }

    for (i = 0; i < m; i++) {
        if (request[i] > avail[i]) {
            printf("Process must wait, resources not available\n");
            exit(1);
        }
    }

    for (i = 0; i < m; i++) {
        avail[i] -= request[i];
        alloc[proc][i] += request[i];
        need[proc][i] -= request[i];
    }

    safety_algo();
}

void accept_request() {
    int i;

    printf("\nEnter the process number : ");
    scanf("%d", &proc);

    printf("Enter the request for process %d : ", proc);
    for (i = 0; i < m; i++)
        scanf("%d", &request[i]);
}

void display_matrix() {
    int i, j;

    printf("\nAllocation\tMax\t\tNeed\n");

    for (i = 0; i < n; i++) {
        for (j = 0; j < m; j++)
            printf("%d ", alloc[i][j]);

        printf("\t\t");

        for (j = 0; j < m; j++)
            printf("%d ", max[i][j]);

        printf("\t\t");

        for (j = 0; j < m; j++)
            printf("%d ", need[i][j]);

        printf("\n");
    }

    printf("\nAvailable : ");
    for (i = 0; i < m; i++)
        printf("%d ", avail[i]);

    printf("\n");
}

void bankers_algo() {
    resource_request_algo ();
}

int main() {
    printf("Enter the number of processes : ");
    scanf("%d", &n);

    printf("Enter the number of resources : ");
    scanf("%d", &m);

    printf("\nEnter the allocation matrix : ");
    accept_matrix(alloc);

    printf("\nEnter the max matrix : ");
    accept_matrix(max);

    accept_available();
    calculate_need();
    display_matrix();

    accept_request();
    bankers_algo();

    return 0;
}
