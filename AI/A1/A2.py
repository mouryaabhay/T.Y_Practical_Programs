# 2. To implement Depth First Search. (DFS)
# Given the simple folder structure below, write a Python program to perform Depth First Search.

# Root
#  |- Folder1
#  |_ Folder2

graph = {
    'Root': ['Folder1', 'Folder2'],
    'Folder1': [],
    'Folder2': []
}

def dfs(node):
  print(node, end=' ')
  for i in graph[node]:
    dfs(i)

print("DFS First Search (DFS) form Root:")
dfs('Root')