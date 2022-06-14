import { useMemo } from "react";
import { makeGetAllGameEntities } from "www/store/game/selectors";
import { useAppSelector } from "www/store/hooks";
import { PlayerHeader } from "../PlayerScreen/PlayerHeader";
import { EntityList } from "../EntityScreen/EntityList";
import { GameHeader } from "./GameHeader";
import { HexGeneratorComponent } from "./HexGeneratorComponent";
import { DiceComponent} from "./DiceComponent";
import { ScoreComponent} from "./ScoreComponent";

import { Row, Col, Container} from 'react-bootstrap';



export function GameScreen() {
  const getAllGameEntities = useMemo(makeGetAllGameEntities, []);
  const entities = useAppSelector(getAllGameEntities);
  console.log("entities");
 
  return (
    <>
      <PlayerHeader color="white" />
      <GameHeader />
      <main>
        <h1>Catan</h1>
        <Container>
          <Row>
            <Col style={{backgroundColor:'#85C1E9', borderRadius: 20}}>
              <div style={{margin:10}}>
                <h4>Inventory:</h4>
                <EntityList entities={entities} />
              </div>  
            </Col>
            <Col style={{backgroundColor:'#FAD7A0', borderRadius: 20}}  md= {{offset:1}}>
              <div style={{margin:10}}>
                <DiceComponent entities={entities}/>
                <ScoreComponent entities = {entities}/>
                <h4>Cost Table:</h4>
                <p>&emsp;Road: 🧱🪵</p>
                <p>&emsp;Town: 🧱🪵🐑🌾</p>
                <p>&emsp;City: 🪨🪨🪨🌾🌾</p>
              </div>
            </Col>
          </Row>
        </Container>
        <HexGeneratorComponent entities={entities}/>
        
        
      </main>
    </>
  );
}
