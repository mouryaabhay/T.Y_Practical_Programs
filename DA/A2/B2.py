# 2. Download the groceries dataset.
# Write a python program to read the dataset and display its information.
# Preprocess the data (drop null values etc.) Convert the categorical values into numeric format.
# Apply the apriori algorithm on the above dataset to generate the frequent itemsets and association rules.

df = pd.read_csv("groceries.csv", header=None)

transactions = []

for i in range(len(df)):
    transactions.append([str(item) for item in df.iloc[i].dropna()])

te = TransactionEncoder()
te_array = te.fit(transactions).transform(transactions)
groceries_df = pd.DataFrame(te_array, columns=te.columns_)
freq_items = apriori(groceries_df, min_support=0.01, use_colnames=True)
rules = association_rules(freq_items, metric="confidence", min_threshold=0.3)

print(freq_items)
print(rules)
