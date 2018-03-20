# Concurrency-computer-science

## guião 3 

### int createAccount(float initialBalance);
### float closeAccount(int id) throws InvalidAccount;
### void deposit(int id, int val) throws InvalidAccount;
### void withdraw(int id, int val) throws InvalidAccount;
### void transfer(int from, int to, float amount) throws InvalidAccount, NotEnoughFunds;
### float totalBalance(int accounts[]) throws InvalidAccount;

## guião 4

### Exercicio 1
### Exercicio em que mudamos de semaphore
### public void putBoundedBuffer(int item) throws InterruptedException
### public int getBoundedBuffer() throws InterruptedException

### Exercicio em que mudamos de semaphore para wait/notifyAll
### public void putBoundedBuffer(int item) throws InterruptedException
### public int getBoundedBuffer() throws InterruptedException

###Exercicio 3
public synchronized void await() throws InterruptedException
