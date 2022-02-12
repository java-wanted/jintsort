# jintsort
Java application to sort integral numbers

## SYNOPSIS

```c
jintsort [-h] [-a|-d] [NUMBERS]
```

## DESCRIPTION

  - Read integral numbers, NUMBERS.
  - Write this numbers in an order.

  NUMBERS is a space-separated list of integral numbers.
  Numbers MUST BE in the range -128 through 127.

  With no NUMBERS, or when NUMBERS is -, read the standard input.

## OPTIONS

  - `h` Print help and exit.

  - `a` Present numbers in the ascending order (by default).
    This option MUST NOT BE used with the option d.

  - `d` Present number in the descending order.
    This option MUST NOT BE used with the option a.

## AUTHOR

  java-wanted <microsoft-wanted@yandex.ru>.

## LICENSE

  This Java application can be used in accordance with rules and limitation of
  License GPLv3+: GNU GPL version 3 or later
  <https://gnu.org/licenses/gpl.html>.

  This is free software: you are free to change and redistribute it. There is NO
  WARRANTY, to the extent permitted by law.
