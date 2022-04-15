package com.tfg.game.components.owneds.Api;


import com.tfg.game.components.owneds.OwnedsController;
import com.tfg.game.components.partnerships.PartnershipController;
import com.tfg.game.components.resources.ResourcesController;
import com.tfg.game.components.typeds.TypedsController;
import com.tfg.game.components.upgradeds.UpgradedsController;
import com.tfg.game.ecs.GameData;
import com.tfg.game.games.api.GamesApi;
import com.tfg.game.players.PlayersController;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/owned")
public class OwnedApi {
    private final OwnedsController ownedsController;
    private final PlayersController playersController;
    private final ResourcesController resourcesController;
    private final TypedsController typedsController;
    private final UpgradedsController upgradedsController;
    private final PartnershipController partnershipController;
    private final GamesApi gamesApi;

    public OwnedApi(OwnedsController ownedsController, PlayersController playersController, ResourcesController resourcesController, TypedsController typedsController, UpgradedsController upgradedsController, PartnershipController partnershipController, GamesApi gamesApi) {
        this.ownedsController = ownedsController;
        this.playersController = playersController;
        this.resourcesController = resourcesController;
        this.typedsController = typedsController;
        this.upgradedsController = upgradedsController;
        this.partnershipController = partnershipController;
        this.gamesApi = gamesApi;
    }

    @PostMapping("{entityId}/{playerName}/own")
    public GameData Own(@PathVariable String entityId, @PathVariable String playerName, @RequestParam String token){
        var player = playersController.findPlayer(playerName).get();
        var ownedTest = ownedsController.own(entityId);

        var inventoryId = ownedsController.findAllByGameAndOwner(ownedTest.getGame(), player).stream()
                    .filter(c -> typedsController.isTyped(c.getEntityId(), "inventory")).findFirst().get().getEntityId();

        var isVertex = typedsController.isTyped(entityId,"vertex");
        var isOwned = ownedTest.getOwner() != null ? true : false;

        var partnership = partnershipController.getPartnership(entityId);
        var isOwnedNeighbour1BySamePlayer = ownedsController.own(partnership.getPartnership1()).getOwner() == null ? false : ownedsController.own(partnership.getPartnership1()).getOwner().getPlayerName() == playerName ? true : false;
        var isOwnedNeighbour2BySamePlayer = ownedsController.own(partnership.getPartnership2()).getOwner() == null ? false : ownedsController.own(partnership.getPartnership2()).getOwner().getPlayerName() == playerName ? true : false;
        var isOwnedNeighbour3BySamePlayer = partnership.getPartnership3() == "" ? false : ownedsController.own(partnership.getPartnership3()).getOwner() == null ? false : ownedsController.own(partnership.getPartnership3()).getOwner().getPlayerName() == playerName ? true : false;

        var partner1Partnership = partnershipController.getPartnership(partnership.getPartnership1());
        var partner2Partnership = partnershipController.getPartnership(partnership.getPartnership2());

        var isOwnedPartner1Neighbour1BySamePlayer= ownedsController.own(partner1Partnership.getPartnership1()).getOwner() == null ? false : ownedsController.own(partner1Partnership.getPartnership1()).getOwner().getPlayerName() == playerName ? true : false;
        var isOwnedPartner1Neighbour2BySamePlayer= ownedsController.own(partner1Partnership.getPartnership2()).getOwner() == null ? false : ownedsController.own(partner1Partnership.getPartnership2()).getOwner().getPlayerName() == playerName ? true : false;
        var isOwnedPartner1Neighbour3BySamePlayer= partner1Partnership.getPartnership3() == "" ? false : ownedsController.own(partner1Partnership.getPartnership3()) == null ? false : ownedsController.own(partner1Partnership.getPartnership3()).getOwner() == null ? false : ownedsController.own(partner1Partnership.getPartnership3()).getOwner().getPlayerName() == playerName ? true : false;

        var isOwnedPartner2Neighbour1BySamePlayer= ownedsController.own(partner2Partnership.getPartnership1()).getOwner() == null ? false : ownedsController.own(partner2Partnership.getPartnership1()).getOwner().getPlayerName() == playerName ? true : false;
        var isOwnedPartner2Neighbour2BySamePlayer= ownedsController.own(partner2Partnership.getPartnership2()).getOwner() == null ? false : ownedsController.own(partner2Partnership.getPartnership2()).getOwner().getPlayerName() == playerName ? true : false;
        var isOwnedPartner2Neighbour3BySamePlayer= partner1Partnership.getPartnership3() == "" ? false : ownedsController.own(partner2Partnership.getPartnership3()).getOwner() == null ? false : ownedsController.own(partner2Partnership.getPartnership3()).getOwner().getPlayerName() == playerName ? true : false;

        boolean resources;
        if(isVertex) {
            if(!isOwned)
                resources = resourcesController.ownTown(inventoryId);
            else {
                var isDifferentPlayer = ownedTest.getOwner().getPlayerName() != playerName ? true : false;

                if(isDifferentPlayer)
                    resources = resourcesController.ownCity(inventoryId, false);
                else
                    resources = resourcesController.ownCity(inventoryId, true);

            }
        }
        else {
            if(isOwned)
                resources = resourcesController.ownRoad(inventoryId, false);
            else {
                if((isOwnedNeighbour1BySamePlayer || isOwnedNeighbour2BySamePlayer) ||
                        (isOwnedPartner1Neighbour1BySamePlayer || isOwnedPartner1Neighbour2BySamePlayer || isOwnedPartner1Neighbour3BySamePlayer) ||
                        (isOwnedPartner2Neighbour1BySamePlayer || isOwnedPartner2Neighbour2BySamePlayer || isOwnedPartner2Neighbour3BySamePlayer))
                    resources = resourcesController.ownRoad(inventoryId, true);
                else
                    resources = resourcesController.ownRoad(inventoryId, false);

            }
        }
        if(resources) {
            if(isVertex && isOwned){
                var upgraded = upgradedsController.upgrade(entityId, true);
                var game = upgraded.getGame();
                return gamesApi.get(game.getGameName(), game.getCreator().getPlayerName(), token);

            }else{
                var owned = ownedsController.own(entityId, player);
                var game = owned.getGame();
                return gamesApi.get(game.getGameName(), game.getCreator().getPlayerName(), token);
            }
        }

        var owned = ownedsController.own(entityId);
        var game = owned.getGame();

        return gamesApi.get(game.getGameName(), game.getCreator().getPlayerName(), token);
    }
}
