import { Grid2, } from '@mui/material';
import { useState } from 'react'
import BoardBtn from './BoardBtn';
import useWinnerManager from '../hooks/useWinnerMannager';

function Tablero({turno, changeTurno, winner, setwinner, tie, settie}) {
    const [clickedButons, setclickedButons] = useState([null, null, null, 
                    null, null, null, 
                    null, null, null]);

    function onClick(id){
        let newArray = clickedButons;
        newArray[id] = turno;
        setclickedButons(newArray);

        changeTurno()
        useWinnerManager(clickedButons, setclickedButons, setwinner, settie)
    }
    
  return (
    <Grid2 container  sx={{justifyContent: "center", alignItems: "center", }} spacing={2}>
        <BoardBtn id={0} value={clickedButons[0]} onClick={()=>onClick(0)} disabled={winner || tie}></BoardBtn>
        <BoardBtn id={1} value={clickedButons[1]} onClick={()=>onClick(1)} disabled={winner || tie}></BoardBtn>
        <BoardBtn id={2} value={clickedButons[2]} onClick={()=>onClick(2)} disabled={winner || tie}></BoardBtn>
        
        <BoardBtn id={3} value={clickedButons[3]} onClick={()=>onClick(3)} disabled={winner || tie}></BoardBtn>
        <BoardBtn id={4} value={clickedButons[4]} onClick={()=>onClick(4)} disabled={winner || tie}></BoardBtn>
        <BoardBtn id={5} value={clickedButons[5]} onClick={()=>onClick(5)} disabled={winner || tie}></BoardBtn>
        
        <BoardBtn id={6} value={clickedButons[6]} onClick={()=>onClick(6)} disabled={winner || tie}></BoardBtn>
        <BoardBtn id={7} value={clickedButons[7]} onClick={()=>onClick(7)} disabled={winner || tie}></BoardBtn>
        <BoardBtn id={8} value={clickedButons[8]} onClick={()=>onClick(8)} disabled={winner || tie}></BoardBtn>
    </Grid2>
  )
}

export default Tablero;