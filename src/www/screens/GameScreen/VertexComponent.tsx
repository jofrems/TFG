
import styled from "styled-components";
import { useAppSelector, useDispatchFormBig } from "www/store/hooks";
import { getPlayerName } from "www/store/player/selectors";
import { own } from "../../components/OwnComponent/actions";

const Vertex = styled.circle`
cursor: pointer;
fill-opacity: 0.4;
stroke: #000;
stroke-width: 1;
transition: transform, fill-opacity, stroke-width;
-webkit-transition: transform, fill-opacity, stroke-width;
transition-duration: 1s;
-webkit-transition-duration: 1s;;
&:hover{
  fill-opacity: 1;
  stroke: #ff0000;
  stroke-width: 5;
}
`
//transform-origin: 50% 50%;

//transform: rotate(30deg);



export function VertexComponent({ entity }: any) { 
  const currentPlayer = useAppSelector(getPlayerName);
  const owned = useDispatchFormBig(own, entity.id, currentPlayer);

  if(entity.type !== "vertex") return null;


    var xOffset = entity.row*65 +66;

    var yOffset = entity.column*32;


    if(entity.column > 1)
        yOffset = yOffset + 45;
    if(entity.column > 3)
        yOffset = yOffset + 50;
    if(entity.column > 5)
        yOffset = yOffset + 50;
    if(entity.column > 7)
        yOffset = yOffset + 50;
    if(entity.column > 9)
        yOffset = yOffset + 50;
    
    var xPos = xOffset;
    var yPos = yOffset -70;
    var entityId = entity.id;

    //generem un color pel jugador aleatoriament
    var colour= '#';
     // this is NOT a great hash but serves as an example
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
    

  return (
      <g id="vertex">
        <Vertex id={entityId} cx={xPos} cy={yPos} r="20" stroke="black" stroke-width="3" fill={colour}  onClick={owned}></Vertex>
      </g>    
  );
}
