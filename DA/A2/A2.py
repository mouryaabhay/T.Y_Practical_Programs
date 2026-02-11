# SET A – 2
my_transactions = [
 ['Laptop', 'Mouse'],
 ['Laptop', 'Bag'],
 ['Laptop', 'Mouse', 'Bag'],
 ['Mouse', 'Bag'],
 ['Laptop', 'Mouse']
]
te = TransactionEncoder()
te_array = te.fit(my_transactions).transform(my_transactions)
my_df = pd.DataFrame(te_array, columns=te.columns_)
display(my_df)
freq_items = apriori(my_df, min_support=0.4, use_colnames=True)
rules = association_rules(freq_items, metric="confidence", min_threshold=0.6)
display(freq_items, rules)