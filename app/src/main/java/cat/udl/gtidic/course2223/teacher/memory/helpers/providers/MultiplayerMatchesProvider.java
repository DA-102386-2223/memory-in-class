package cat.udl.gtidic.course2223.teacher.memory.helpers.providers;

import java.util.ArrayList;
import java.util.List;

import cat.udl.gtidic.course2223.teacher.memory.models.MultiplayerMatch;

public class MultiplayerMatchesProvider {
    public List<MultiplayerMatch> getLaMevaLlista() {
        return laMevaLlista;
    }

    List<MultiplayerMatch> laMevaLlista = new ArrayList<>();

    public MultiplayerMatchesProvider(){
        laMevaLlista.add(new MultiplayerMatch("albert"));
        laMevaLlista.add(new MultiplayerMatch("pep"));
        laMevaLlista.add(new MultiplayerMatch("jesus"));
        laMevaLlista.add(new MultiplayerMatch("nen jesus"));
        laMevaLlista.add(new MultiplayerMatch("maria"));
    }
}
