package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }
    @Test
    public void getFavoritesWithSuccess()
    {
        List<Neighbour> neighbours = service.getNeighbours();
        Neighbour neighbour = neighbours.get(0);
        neighbour.setFavorite(true);
        List<Neighbour> favorites = service.getFavoritesNeighbour();
        assertThat(favorites.size(), is(1));
        assertTrue(favorites.contains(neighbour));
    }

    @Test
    public void neighbourIsFavorite() {
        List<Neighbour> neighboursFav = service.getFavoritesNeighbour();
        List<Neighbour> neighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        List<Neighbour> neighboursFavExpected = new ArrayList<>();

        for (Neighbour neighbour : neighbours) {
            if (neighbour.getFavorite())
                neighboursFavExpected.add(neighbour);
        }
        assertTrue(neighboursFavExpected.equals(neighboursFav));
    }
}
