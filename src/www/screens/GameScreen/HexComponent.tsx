import styled from "styled-components";


const Hex = styled.polygon`
cursor: pointer;
fill-opacity: 0.4;
stroke: #000;
stroke-width: 1;
transition: transform, fill-opacity, stroke-width;
-webkit-transition: transform, fill-opacity, stroke-width;
transition-duration: 1s;
-webkit-transition-duration: 1s;;
transform: rotate(30deg);
&:hover{
  fill-opacity: 1;
  stroke: #ff0000;
  stroke-width: 5;
}
`
//transform-origin: 50% 50%;

//transform: rotate(30deg);



export function HexComponent({ entity }: any) {

  var SEA = -1;
  var DESSERT = -2;
  var LUMBER = 0;
  var GRAIN = 1;
  var WOOL = 2;
  var BRICK = 3;
  var ORE = 4;

  var x = [0,37,112,150,112,37];
  var y = [75,10,10,75,140,140];

  var xOffsetDeg = 113*entity.column;
  var yOffsetDeg = 65*entity.row;
  var xJump = 113*entity.row;
  var yJump = 65*entity.column;
  var finalLoc = `${x[0]+ xJump -300+ xOffsetDeg},${y[0]+yJump-yOffsetDeg-75} ${x[1]+xJump + xOffsetDeg-300},${y[1]+yJump-yOffsetDeg-75} ${x[2]+xJump + xOffsetDeg -300},${y[2]+yJump-yOffsetDeg-75} ${x[3]+xJump + xOffsetDeg-300},${y[3]+yJump-yOffsetDeg-75} ${x[4]+xJump + xOffsetDeg -300},${y[4]+yJump-yOffsetDeg-75} ${x[5]+xJump + xOffsetDeg-300},${y[5]+yJump-yOffsetDeg-75}`
  var xTextOffset = 130*entity.row + entity.column*60-200; 
  var textX = `${x[0]+ xTextOffset}`;
  var textY = `${y[0]+115*entity.column-180}`;
  var color = "";

      switch(entity.TileType){
        case DESSERT:
          color="#663300";
          break;
        case SEA:
          color="#0080FF";
          break;
        case LUMBER:
          color="#006600";
          break;
        case GRAIN:
          color="#FFFF33";
          break;
        case WOOL:
          color="#80FF00";
          break;
        case BRICK:
          color="#FF9933";
          break;
        case ORE:
          color="#C0C0C0";
          break;
        default:
          color="#000000";
      }      
  return (
      <g id="hex">
        <Hex fill={color} points={finalLoc}></Hex>
        <text x={textX} y={textY}> {entity.TileWeight}</text>
      </g>    
  );
}
