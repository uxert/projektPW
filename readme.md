TODO:
1) Dodać animacje przemieszczania się
2) Dodać stos itemów czekających na recepcjonistę
3) Ogarnąć to, że UI jest diabolicznie brzydkie

# Projekt z przedmiotu programowanie współbieżne

Ma pokazać za pomocą graficznej aplikacji współbieżny przebieg procesów w zakładzie naprawczo-usługowym.

# Treść zadania:

Zadanie nr: PW-10/2024\
Język implementacji: Java\
Środowisko implementacyjne: Eclipse, Intelij IDEA, Netbeans\
Termin wykonania: ostatnie zajęcia\
Podstawowe wymagania:\
a. liczba procesów sekwencyjnych powinna być dobrana z wyczuciem tak, aby zachować czytelność interfejsu i
jednocześnie umożliwić zobrazowanie reprezentatywnych przykładów,\
b. kod źródłowy programu musi być tak skonstruowany, aby można było „swobodnie” modyfikować liczbę
procesów sekwencyjnych (za wyjątkiem zadań o ściśle określonej liczbie procesów),\
c. graficzne zobrazowanie działania procesów współbieżnych,\
d. odczyt domyślnych danych wejściowych ze sformatowanego, tekstowego pliku danych (xml, properties, inne),\
e. możliwość modyfikacji danych wejściowych poprzez GUI.

Sprawozdanie (w formie elektronicznej) powinno zawierać następujące elementy:
1) stronę tytułową,
2) niniejszą treść zadania,
3) syntetyczny opis problemu – w tym wszystkie przyjęte założenia,
4) wykaz własnych procesów sekwencyjnych,
5) wykaz współdzielonych zasobów,
6) wykaz wyróżnionych punktów synchronizacji,
7) wykaz obiektów synchronizacji,
8) listing programu.

# Problem do rozwiązania: Zakład usługowy
Założenia:
Pewien zakład usługowy zatrudnia łącznie 4 pracowników. Jedna osoba przyjmuje zamówienia na naprawę sprzętu X a trzy pozostałe osoby realizują te naprawy. Zakład może maksymalnie pomieścić 100 elementów sprzętu X. Osoba przyjmująca zamówienia notuje adresy właścicieli oddawanego przez nich sprzętu do naprawy i dołącza je do przyjmowanego egzemplarza. Potem odkłada sprzęt na półkę. Gdy któryś z trzech "fizycznych" naprawiaczy jest wolny to bierze odebrany element, naprawia go, opakowuje, przykleja adres odbiorcy i wysyła do zamawiającego naprawę. Dopiero wówczas element znika z ewidencji zakładu naprawczego.