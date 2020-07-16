package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    @Override
    public void updateFavorites(Neighbour neighbour) {
        for (int i = 0; i < neighbours.size(); i++) {
            Neighbour currentNeighbours = neighbours.get(i);
            if (currentNeighbours.getId() == neighbour.getId()) {
                currentNeighbours.setFavorite(neighbour.getFavorite());
                break;
            }
        }
    }


    @Override
    public List<Neighbour> getFavoritesNeighbour() {
        List<Neighbour> neighboursFavorites = new ArrayList<>();
        for (int i = 0; i < neighbours.size(); i++) {
            Neighbour currentNeighbours = neighbours.get(i);
            if (currentNeighbours.getFavorite()) {
                neighboursFavorites.add(currentNeighbours);
            }
        }
        return neighboursFavorites;
    }

}
