# 3. To implement the Generate and Test strategy
# A digital lock uses a 3-digit code. To find the correct code by generating every possible combination from 000 to 999 and testing each one until the correct code is found.
# e.g. Target code is : 576

target = "576"

for i in range(1000):  # 000 to 999
    guess = str(i).zfill(3)  # Generate 3-digit code with leading zeros
    print(f"Trying code: {guess}")
    if guess == target:
        print(f"Code found: {guess}")
        break