# SET B – 2
df = pd.read_csv("groceries.csv", engine="python", on_bad_lines="skip", head)
transactions = []
for i in range(len(df)):
 # transactions.append([str(item) for item in df.iloc[i] if pd.notna(item
 transactions.append([str(item) for item in df.iloc[i].dropna()])
te = TransactionEncoder()
te_array = te.fit(transactions).transform(transactions)
groceries_df = pd.DataFrame(te_array, columns=te.columns_)
freq_items = apriori(groceries_df, min_support=0.01, use_colnames=True)
rules = association_rules(freq_items, metric="confidence", min_threshold=0.3)
print(freq_items)
print(rules)