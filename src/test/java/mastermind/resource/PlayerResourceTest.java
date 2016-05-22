package mastermind.resource;

import mastermind.Player;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class PlayerResourceTest {

    @Test
    public void shouldParseToDomainObject() {
        PlayerResource resource = new PlayerResource();
        resource.setName("playerName");

        Player player = resource.toDomain();
        Player expectedPlayer = new Player("playerName");

        assertThat(player, is(expectedPlayer));
    }
}
