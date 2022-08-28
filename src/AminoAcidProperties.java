import java.util.HashMap;

public class AminoAcidProperties {
    private final HashMap<String, Integer> codonCounts;
    private final String DNA;

    public AminoAcidProperties(final String DNA) {
        this.codonCounts = new HashMap<>();
        this.DNA = DNA;
    }

    private void buildCodonMap(final int startReadingFrame) {
        this.codonCounts.clear();
        for (int i = startReadingFrame; i < this.DNA.length(); i += 3) {
            try {
                if (this.codonCounts.containsKey(this.DNA.substring(i, i + 3))) {
                    this.codonCounts.put(this.DNA.substring(i, i + 3), this.codonCounts.get(this.DNA.substring(i, i + 3)) + 1);
                } else {
                    this.codonCounts.put(this.DNA.substring(i, i + 3), 1);
                }
            } catch (final Exception e) {
                // do nothing
            }
        }
    }

    public void printCodonCounts(final int startReadingFrame, final int start, final int end) {
        buildCodonMap(startReadingFrame);
        int count = 0;
        for (final String codon : this.codonCounts.keySet()) {
            if ((this.codonCounts.get(codon) >= start) && (this.codonCounts.get(codon) <= end)) {
                count++;
                System.out.println(codon + "\t" + this.codonCounts.get(codon));
            }
        }
        System.out.println(count);
    }
}