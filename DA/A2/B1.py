# SET B – 1
df = pd.read_csv("Market_Basket_Optimisation.csv", header=None)
transactions = []
for i in range(len(df)):
 transactions.append([str(item) for item in df.iloc[i].dropna()])
te = TransactionEncoder()
te_array = te.fit(transactions).transform(transactions)
basket_df = pd.DataFrame(te_array, columns=te.columns_)
display(basket_df)
freq_items = apriori(basket_df, min_support=0.01, use_colnames=True)
rules = association_rules(freq_items, metric="confidence", min_threshold=0.3)
display(freq_items, rules)