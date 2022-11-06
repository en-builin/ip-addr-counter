# ip-addr-counter

### Objective

Application counts a number of unique ip-addresses (v4) in a file.

The input file should contains ip-addresses line-by-line, each address must be correct.

Application can process any huge file, designed and tested for using on 120Gb file.

### Usage

build:
```
mvn package
```

Run:
```
java -jar ip-addr-counter.jar [input-file-name]
```

### Implemetation

Project contains a simple implementation that counting unique strings in ```HashSet```. This implementation is awful in
memory usage and processing time. This variant kept in project just to show main idea.

Main implementation set in ```CounterBitSetImpl``` and ```IpToIntConverter```. Main entry point is class ```IpAddressesCounter```.

#### Storage for counting

To reduce memory usage every IP address converts to primitive int. This allows to use less memory, than string type.

4B (256^4) addresses is maximum that we have. Every possible address can be stored in a cell with two options (1 bit):
1 - we met this address in file, 0 - we still didn't meet this address.

So we need to use fixed size array of bits, it will reduce time for counting. We need 256^4 elements. We can't use 
arrays of booleans, because boolean will use one byte for every element and we will be needed 4Gb of RAM.

Useful storage is ```java.utils.BitSet``` - it implements storage of bits with setting bits values by indexes.

#### IP-address to int converting

To convert IP-address from string to int (index in BitSet) we can use string parsing algorithms, but we need to find
fast implementation, without using regex and without checking format and throwing exceptions. Because we need to process
a huge amount of addresses and method should work very fast.

I ported IP-address parsing from JDK (sun.utils) and modified it to prevent error-checking and unnecessary processing.
It also reduced processing time.
