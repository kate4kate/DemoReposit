package academy.lesson11.subscr;

public enum  Gender {
    MALE("м"),
    FEMALE("ж");
    private String ru;

    Gender(String ru) {
        this.ru = ru;
    }

    @Override
    public String toString() {
        return ru;

    }
    }