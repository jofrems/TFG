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
transform-origin: 50% 50%;
&:hover{
  fill-opacity: 1;
  stroke: #ff0000;
  stroke-width: 5;
}
`

//transform: rotate(30deg);



export function HexComponent({ entity }: any) {
  var SEA = -1;
  var DESSERT = -2;
  var LUMBER = 0;
  var GRAIN = 1;
  var WOOL = 2;
  var BRICK = 3;
  var ORE = 4;

  var mapSize = 7;

  var locations = new Array(7).fill(new Array(7).fill(`#`));
  console.log(`new hex`);
  
  //var rowLocations = ["", "", "", "", "", "", ""];
  var x = [0,37,112,150,112,37];
  var y = [75,10,10,75,140,140];



  /*for(var i = 0; i < mapSize; i++){
    //locations.push(rowLocations);
    for(var j = 0; j < mapSize; j++){
      var auxString = `${x[0]},${y[0]} ${x[1]},${y[1]} ${x[2]},${y[2]} ${x[3]},${y[3]} ${x[4]},${y[4]} ${x[5]},${y[5]}`;
      locations[j][i] = auxString;

     // console.log(`line `+ i + `: `+locations[i][j]);

      for(var k = 0; k < x.length; k++){
        var auxX = x[k]+100;
        x[k] = auxX;
      }
    }
    x = [0,37,112,150,112,37];
    for(var l = 0; l < y.length; l++){
      var auxY = y[l] + 100;
      y[l] = auxY;
    }
  }*/
  var xJump = 100*entity.row;
  var yJump = 100*entity.column;
  var finalLoc = `${x[0]+ xJump},${y[0]+yJump} ${x[1]+xJump},${y[1]+yJump} ${x[2]+xJump},${y[2]+yJump} ${x[3]+xJump},${y[3]+yJump} ${x[4]+xJump},${y[4]+yJump} ${x[5]+xJump},${y[5]+yJump}`
  //var locations = [[0,1,2,"0,75 37,10 112,10 150,75 112,140 37,140",  "30,75 67,10 142,10 180,75 142,140 67,140",   "60,75 97,10 172,10 210,75 172,140 97,140",   "0,75 37,10 112,10 150,75 112,140 37,140"pos7]]

  var color = "";
  var locX = 0;
  var locY = 0;
  if(entity.type === "tile"){
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
      
      
      locX = entity.column;
      locY = entity.row;
      
     /* console.log(locations);
      console.log(`especific loc:` +locations[locX][locY]);
      console.log(`entity row/locY: ` +entity.row +` entity column/locX:` +entity.column);*/

      console.log(finalLoc);
    
  };
  return (
      <g id="hex">
        <Hex  fill={color} points={finalLoc}></Hex>
        <text x="700" y="700"> {entity.TileWeight}</text>
      </g>
  );
}
