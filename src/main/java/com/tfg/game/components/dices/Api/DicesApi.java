package com.tfg.game.components.dices.Api;


import com.tfg.game.components.dices.DicesController;
import com.tfg.game.components.neighbours.Neighbours;
import com.tfg.game.components.neighbours.NeighboursController;
import com.tfg.game.components.owneds.Owned;
import com.tfg.game.components.owneds.OwnedsController;
import com.tfg.game.components.resources.ResourcesController;
import com.tfg.game.components.typeds.TypedsController;
import com.tfg.game.ecs.GameData;
import com.tfg.game.games.GamesController;
import com.tfg.game.games.api.GamesApi;
import com.tfg.game.players.PlayersController;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/dice")
public class DicesApi {
    private final DicesController dicesController;
    private final PlayersController playersController;
    private final ResourcesController resourcesController;
    private final TypedsController typedsController;
    private final NeighboursController neighboursController;
    private final OwnedsController ownedsController;
    private final GamesController gamesController;

    private final GamesApi gamesApi;

    public static final int SEA = -1;
    public static final int DESSERT = -2;
    public static final int LUMBER = 0;
    public static final int GRAIN = 1;
    public static final int WOOL = 2;
    public static final int BRICK = 3;
    public static final int ORE = 4;

    public DicesApi(DicesController dicesController, PlayersController playersController, ResourcesController resourcesController, TypedsController typedsController, NeighboursController neighboursController, OwnedsController ownedsController, GamesController gamesController, GamesApi gamesApi) {
        this.dicesController = dicesController;
        this.playersController = playersController;
        this.resourcesController = resourcesController;
        this.typedsController = typedsController;
        this.neighboursController = neighboursController;
        this.ownedsController = ownedsController;
        this.gamesController = gamesController;

        this.gamesApi = gamesApi;
    }

    @PostMapping("{entityId}/{playerName}/roll")
    public GameData Roll(@PathVariable String entityId, @PathVariable String playerName, @RequestParam String token){
        var dices = dicesController.getDices(entityId);
        var player = playersController.findPlayer(playerName).get();
        var game = dices.getGame();

        var isCurrentPlayerTurn = gamesController.isCurrentPlayerTurn(game, player);
        if(isCurrentPlayerTurn) {
            dices = dicesController.rollDices(entityId);
            var diceSum = dices.getSumDices();

            var neighbours = neighboursController.findAllByGame(game);

            for (Neighbours n : neighbours) {
                Owned currentEntity = ownedsController.own(n.getEntityId());
                if (currentEntity.getOwner() != null) {
                    if (n.getNeighbourWeight() == diceSum) {
                        var inventoryId = ownedsController.findAllByGameAndOwner(game, currentEntity.getOwner()).stream()
                                .filter(c -> typedsController.isTyped(c.getEntityId(), "inventory")).findFirst().get().getEntityId();

                        switch (n.getNeighbourType()) {
                            case LUMBER:
                                resourcesController.incOrDecLumber(inventoryId, 1, true);
                                break;
                            case WOOL:
                                resourcesController.incOrDecWool(inventoryId, 1, true);
                                break;
                            case BRICK:
                                resourcesController.incOrDecBrick(inventoryId, 1, true);
                                break;
                            case GRAIN:
                                resourcesController.incOrDecGrain(inventoryId, 1, true);
                                break;
                            case ORE:
                                resourcesController.incOrDecOre(inventoryId, 1, true);
                                break;
                            default:
                                break;
                        }
                    }
                    if (n.getNeighbour2Weight() == diceSum) {
                        var inventoryId = ownedsController.findAllByGameAndOwner(game, currentEntity.getOwner()).stream()
                                .filter(c -> typedsController.isTyped(c.getEntityId(), "inventory")).findFirst().get().getEntityId();

                        switch (n.getNeighbour2Type()) {
                            case LUMBER:
                                resourcesController.incOrDecLumber(inventoryId, 1, true);
                                break;
                            case WOOL:
                                resourcesController.incOrDecWool(inventoryId, 1, true);
                                break;
                            case BRICK:
                                resourcesController.incOrDecBrick(inventoryId, 1, true);
                                break;
                            case GRAIN:
                                resourcesController.incOrDecGrain(inventoryId, 1, true);
                                break;
                            case ORE:
                                resourcesController.incOrDecOre(inventoryId, 1, true);
                                break;
                            default:
                                break;
                        }
                    }
                    if (n.getNeighbour3Weight() == diceSum) {
                        var inventoryId = ownedsController.findAllByGameAndOwner(game, currentEntity.getOwner()).stream()
                                .filter(c -> typedsController.isTyped(c.getEntityId(), "inventory")).findFirst().get().getEntityId();

                        switch (n.getNeighbour3Type()) {
                            case LUMBER:
                                resourcesController.incOrDecLumber(inventoryId, 1, true);
                                break;
                            case WOOL:
                                resourcesController.incOrDecWool(inventoryId, 1, true);
                                break;
                            case BRICK:
                                resourcesController.incOrDecBrick(inventoryId, 1, true);
                                break;
                            case GRAIN:
                                resourcesController.incOrDecGrain(inventoryId, 1, true);
                                break;
                            case ORE:
                                resourcesController.incOrDecOre(inventoryId, 1, true);
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }

        return gamesApi.get(game.getGameName(), game.getCreator().getPlayerName(), token);
    }
}
