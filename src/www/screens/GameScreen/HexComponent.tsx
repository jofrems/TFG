import styled from "styled-components";
import "./hex.css";

const Hex = styled.polygon`
cursor: pointer;
fill-opacity: 0.4;
stroke: #000;
stroke-width: 1;
transform-box: fill-box;
transform-origin: center;
transform: rotate(30deg);
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

  var xJump = entity.row * 66;//113*entity.row;
  var xOffsetDeg = 129*entity.column;
  var yOffsetDeg = 113*entity.row;
  var yJump = 0;//65*entity.column;
  var finalLoc = `${x[0]+ xOffsetDeg + xJump+ 20},${y[0]+yOffsetDeg} ${x[1] + xOffsetDeg + xJump + 20},${y[1]+yOffsetDeg } ${x[2] + xOffsetDeg + xJump + 20},${y[2]+yOffsetDeg } ${x[3] +xOffsetDeg + xJump + 20},${y[3]+yOffsetDeg } ${x[4] + xOffsetDeg + xJump + 20},${y[4]+yOffsetDeg} ${x[5] + xOffsetDeg + xJump + 20},${y[5]+yOffsetDeg }`
 
  var xTextOffset = 129 *entity.column + entity.row *66; 
  var textX = `${x[0]+ xTextOffset + 85}`;
  var textY = `${y[0]+115*entity.row}`;
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
