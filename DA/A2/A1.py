# SET A – 1
import pandas as pd
from mlxtend.preprocessing import TransactionEncoder
from mlxtend.frequent_patterns import apriori, association_rules
transactions = [
 ['Bread', 'Milk'],
 ['Bread', 'Diaper', 'Beer', 'Eggs'],
 ['Milk', 'Diaper', 'Beer', 'Coke'],
 ['Bread', 'Milk', 'Diaper', 'Beer'],
 ['Bread', 'Milk', 'Diaper', 'Coke']
]
te = TransactionEncoder()
te_array = te.fit(transactions).transform(transactions)
df = pd.DataFrame(te_array, columns=te.columns_)
display(df)
freq_items = apriori(df, min_support=0.4, use_colnames=True)
rules = association_rules(freq_items, metric="confidence", min_threshold=0.7)
display(freq_items, rules)