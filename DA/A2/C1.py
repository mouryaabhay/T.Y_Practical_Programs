# SET C – 1
from itertools import combinations
def apriori_custom(transactions, min_support):
 items = set(item for t in transactions for item in t)
 itemsets = [{item} for item in items]
 def support(itemset):
 return sum(1 for t in transactions if itemset.issubset(t)) / len(tra
 freq = []
 k = 1
 current = itemsets
 while current:
 valid = []
 for item in current:
 if support(item) >= min_support:
 freq.append(item)
 valid.append(item)
 current = []
 for i in range(len(valid)):
 for j in range(i+1, len(valid)):
 union = valid[i].union(valid[j])
 if len(union) == k+1 and union not in current:
 current.append(union)
 k += 1
 return freq
nsactions = [
 {'Bread', 'Milk'},
 {'Bread', 'Diaper', 'Beer', 'Eggs'},
 {'Milk', 'Diaper', 'Beer', 'Coke'},
 {'Bread', 'Milk', 'Diaper', 'Beer'},
  {'Bread', 'Milk', 'Diaper', 'Coke'}
]
apriori_custom(transactions, min_support=0.4)