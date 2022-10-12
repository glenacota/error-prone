The problem we're trying to prevent is bad switches

``` {.bad}

enum Suit {HEARTS, CLUBS, SPADES, DIAMONDS}

private void foo(Suit suit) {
  switch(suit) {
    case HEARTS:
      hearts();
      break;
    case CLUBS:
    case SPADES:
    case DIAMONDS:
      other();
  }
}
```

``` {.good}
private void foo(Suit suit) {
  switch(suit) {
    case HEARTS -> hearts();
    case CLUBS, SPADES, DIAMONDS -> other();
  }
}
```
