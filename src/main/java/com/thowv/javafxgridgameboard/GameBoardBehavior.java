package com.thowv.javafxgridgameboard;

import java.io.Serializable;
import java.util.ArrayList;

public class GameBoardBehavior implements Serializable {
    private GameBoard gameBoardControl;
    private int gameBoardSize;
    private GameBoardTile[][] gameBoardTiles;

    public GameBoardBehavior(GameBoard gameBoardControl, int gameBoardSize) {
        this.gameBoardControl = gameBoardControl;
        this.gameBoardSize = gameBoardSize;
    }

    // region Behavior
    public void resetGameBoardTiles() {
        gameBoardTiles = gameBoardControl.getGameBoardSkin().createGameBoardTiles(gameBoardSize);
    }

    public void clearDecoratedGameBoardTiles() {
        ArrayList<GameBoardTile> matchedGameBoardTiles = getGameBoardTilesByTypes(
                new GameBoardTileType[] { GameBoardTileType.VISIBLE, GameBoardTileType.INTERACTABLE });

        // Hide all type matched game board tiles
        for (GameBoardTile gameBoardTile : matchedGameBoardTiles) {
            gameBoardTile.setGameBoardTileType(GameBoardTileType.HIDDEN);
        }
    }

    public int countTilesByType(GameBoardTileType gameBoardTileType) {
        int tileCount = 0;

        for (GameBoardTile[] gameBoardTilesX : gameBoardTiles) {
            for (GameBoardTile gameBoardTile : gameBoardTilesX) {
                if (gameBoardTile.getGameBoardTileType() == gameBoardTileType)
                    tileCount++;
            }
        }

        return tileCount;
    }
    // endregion

    // region Getters and setters
    public int getGameBoardSize() {
        return gameBoardSize;
    }

    public void copyGameBoardTiles(GameBoardTile[][] gameBoardTiles) {
        for (GameBoardTile[] gameBoardTileArray : gameBoardTiles) {
            for (GameBoardTile gameBoardTile : gameBoardTileArray) {
                int xCord = gameBoardTile.getXCord();
                int yCord = gameBoardTile.getYCord();
                GameBoardTileType tileType = gameBoardTile.getGameBoardTileType();

                this.gameBoardTiles[xCord][yCord].setGameBoardTileType(tileType);
            }
        }
    }

    public void copyGameBoardTiles(ArrayList<GameBoardTile> gameBoardTiles) {
        for (GameBoardTile gameBoardTile : gameBoardTiles) {
            int xCord = gameBoardTile.getXCord();
            int yCord = gameBoardTile.getYCord();
            GameBoardTileType tileType = gameBoardTile.getGameBoardTileType();

            this.gameBoardTiles[xCord][yCord].setGameBoardTileType(tileType);
        }
    }

    public void setGameBoardTileType(int xCord, int yCord, GameBoardTileType gameBoardTileType) {
        gameBoardTiles[xCord][yCord].setGameBoardTileType(gameBoardTileType);
    }

    public void setGameBoardTileTypes(ArrayList<GameBoardTile> gameBoardTiles, GameBoardTileType forcedType) {
        for (GameBoardTile gameBoardTile : gameBoardTiles) {
            gameBoardTile.setGameBoardTileType(forcedType);
        }
    }

    public GameBoardTile[][] getAllGameBoardTiles() {
        return gameBoardTiles;
    }

    public GameBoardTile[][] getAllGameBoardTilesCopy() {
        GameBoardTile[][] gameBoardTileCopies = new GameBoardTile[gameBoardSize][gameBoardSize];

        for (int i = 0; i < gameBoardSize; i++) {
            for (int j = 0; j < gameBoardSize; j++) {
                gameBoardTileCopies[i][j] = new GameBoardTile(gameBoardTiles[i][j]);
            }
        }

        return gameBoardTileCopies;
    }

    public void setGameBoardTiles(GameBoardTile[][] gameBoardTiles) {
        this.gameBoardTiles = gameBoardTiles;
    }

    public GameBoardTile getGameBoardTile(int xCord, int yCord) {
        return gameBoardTiles[xCord][yCord];
    }

    public ArrayList<GameBoardTile> getGameBoardTilesByType(GameBoardTileType gameBoardTileType) {
        return getGameBoardTilesByTypes(new GameBoardTileType[] { gameBoardTileType });
    }

    public ArrayList<GameBoardTile> getGameBoardTilesByTypes(GameBoardTileType[] gameBoardTileTypes) {
        ArrayList<GameBoardTile> typeMatchedBoardTiles = new ArrayList<>();

        for (GameBoardTile[] gameBoardTilesX : gameBoardTiles) {
            for (GameBoardTile gameBoardTile : gameBoardTilesX) {
                for (GameBoardTileType gameBoardTileType : gameBoardTileTypes) {
                    if (gameBoardTile.getGameBoardTileType() == gameBoardTileType)
                        typeMatchedBoardTiles.add(gameBoardTile);
                }
            }
        }

        return typeMatchedBoardTiles;
    }
    // endregion
}
