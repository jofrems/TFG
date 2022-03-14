import styled from "styled-components";


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
  if(entity.type !== "vertex") return null;

    var yOffset = entity.column*32;
    var xOffset = entity.row*65 +66;

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

  return (
      <g id="vertex">
        <Vertex cx={xPos} cy={yPos} r="20" stroke="black" stroke-width="3" fill="white"></Vertex>
      </g>    
  );
}
