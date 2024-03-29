
import styled from "styled-components";
import { useAppSelector, useDispatchFormBig } from "www/store/hooks";
import { getPlayerName } from "www/store/player/selectors";
import { own } from "../../components/OwnComponent/actions";

const Road = styled.rect`
cursor: pointer;
transform-box: fill-box;
transform-origin: center;
`
const EastRoad = styled.rect`
cursor: pointer;
transform-box: fill-box;
transform-origin: center;
transform: rotate(30deg);
`

const WestRoad = styled.rect`
cursor: pointer;
transform-box: fill-box;
transform-origin: center;
transform: rotate(-30deg);

`

const WestEastRoad = styled.rect`
cursor: pointer;
transform-box: fill-box;
transform-origin: center;
transform: rotate(90deg);
`

/*
cursor: pointer;
fill-opacity: 0.4;
stroke: #000;
stroke-width: 1;
transition: transform, fill-opacity, stroke-width;
-webkit-transition: transform, fill-opacity, stroke-width;
transition-duration: 1s;
-webkit-transition-duration: 1s;;
transform-box: fill-box;
transform-origin: center;
transform: rotate(30deg);
&:hover{
  fill-opacity: 1;
  stroke: #ff0000;
  stroke-width: 5;
}*/



export function RoadComponent({ entity }: any) { 
  const currentPlayer = useAppSelector(getPlayerName);
  const owned = useDispatchFormBig(own, entity.id, currentPlayer);

  if(entity.type !== "road") return null;

   // console.log("row: "+ entity.row + "column: "+entity.column);
    var xOffset = entity.row*33;

    var yOffset = entity.column*56;

    
    var xPos = xOffset+313;
    var yPos = yOffset+125;
    var entityId = entity.id;

    //generem un color pel jugador aleatoriament
    var colour= '#';

    if(entity.owner != null){
        var hash = 0;
        for (var i = 0; i < entity.owner!.length; i++) {
            hash = entity.owner!.charCodeAt(i) + ((hash << 5) - hash);
        }
        for (var i = 0; i < 3; i++) {
             var value = (hash >> (i * 8)) & 0xFF;
            colour += ('00' + value.toString(16)).substr(-2);
        }
    }else{
        colour = "white"
    }
    
    if(entity.column === 2 || entity.column === 6 || entity.column === 10){
        if(entity.row === 3 || entity.row === 7 || entity.row === 11 || entity.row === 15 || entity.row ===19){
            return (
                <g id="road">
                    <WestRoad id={entityId} x={xPos} y={yPos} width="80" height="20"  stroke={entity.owner != null ? "black" : "red"} strokeWidth={entity.owner != null ? 3 : 0  } fill={entity.owner != null ? colour : "white"} fillOpacity={entity.owner != null ? 1 : 0} onClick={owned}></WestRoad>
                </g>    
            );
        }else if(entity.row=== 1 ||  entity.row === 5 || entity.row === 9 || entity.row === 13|| entity.row === 17){
                return (
                    <g id="road">
                      <EastRoad id={entityId} x={xPos} y={yPos} width="80" height="20"  stroke={entity.owner != null ? "black" : "red"} strokeWidth={entity.owner != null ? 3 : 0  } fill={entity.owner != null ? colour : "white"} fillOpacity={entity.owner != null ? 1 : 0} onClick={owned}></EastRoad>
                    </g>    
                );
        }else{
                return (
                    <g id="road">
                      <Road id={entityId} x={xPos} y={yPos} width="80" height="20"  stroke={entity.owner != null ? "black" : "red"} strokeWidth={entity.owner != null ? 3 : 0  } fill={entity.owner != null ? colour : "white"} fillOpacity={entity.owner != null ? 1 : 0} onClick={owned}></Road>
                    </g>    
                );
        }
    }else if(entity.column ===0 || entity.column ===4 || entity.column ===8){
        if(entity.row === 3 || entity.row === 7 || entity.row === 11 || entity.row === 15 || entity.row ===19){
            return (
                <g id="road">
                    <EastRoad id={entityId} x={xPos} y={yPos} width="80" height="20"  stroke={entity.owner != null ? "black" : "red"} strokeWidth={entity.owner != null ? 3 : 0  } fill={entity.owner != null ? colour : "white"} fillOpacity={entity.owner != null ? 1 : 0} onClick={owned}></EastRoad>
                </g>    
            );
            }else if(entity.row=== 1 ||  entity.row === 5 || entity.row === 9 || entity.row === 13 || entity.row === 17){
                return (
                    <g id="road">
                      <WestRoad id={entityId} x={xPos} y={yPos} width="80" height="20"  stroke={entity.owner != null ? "black" : "red"} strokeWidth={entity.owner != null ? 3 : 0  } fill={entity.owner != null ? colour : "white"} fillOpacity={entity.owner != null ? 1 : 0} onClick={owned}></WestRoad>
                    </g>    
                );
            }else{
                return (
                    <g id="road">
                      <Road id={entityId} x={xPos} y={yPos} width="80" height="20"  stroke={entity.owner != null ? "black" : "red"} strokeWidth={entity.owner != null ? 3 : 0  } fill={entity.owner != null ? colour : "white"} fillOpacity={entity.owner != null ? 1 : 0} onClick={owned}></Road>
                    </g>    
                );
            }
    }else if (entity.column % 2 ===1){
        return (
            <g id="road">
                <WestEastRoad id={entityId} x={xPos} y={yPos} width="80" height="20"  stroke={entity.owner != null ? "black" : "red"} strokeWidth={entity.owner != null ? 3 : 0  } fill={entity.owner != null ? colour : "white"} fillOpacity={entity.owner != null ? 1 : 0} onClick={owned} ></WestEastRoad>
            </g> 
        );
    }
    else{
        return (
            <g id="road">
              <Road id={entityId} x={xPos} y={yPos} width="80" height="20"  stroke={entity.owner != null ? "black" : "red"} strokeWidth={entity.owner != null ? 3 : 0  } fill={entity.owner != null ? colour : "white"} fillOpacity={entity.owner != null ? 1 : 0} onClick={owned}></Road>
            </g>    
        );
    }
  
}
