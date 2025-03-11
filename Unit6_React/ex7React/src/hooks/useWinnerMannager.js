export default function useWinnerManager(clickedButons, setclickedButons, setwinner, settie){
    useCheckwinner(setwinner, clickedButons, setclickedButons)
    useCheckTie(settie, clickedButons, setclickedButons)
}

function useCheckwinner(setwinner, clickedButons, setclickedButons){
    const lines = [
        [0, 1, 2],
        [3, 4, 5],
        [6, 7, 8],
        [0, 3, 6],
        [1, 4, 7],
        [2, 5, 8],
        [0, 4, 8],
        [2, 4, 6],
      ];
      for (let i = 0; i < lines.length; i++) {
        const [a, b, c] = lines[i];
        if (clickedButons[a] && 
            clickedButons[a] === clickedButons[b] && 
            clickedButons[a] === clickedButons[c]) {
          setwinner(true)
          setclickedButons([null, null, null, null, null, null, null, null, null])
        }
      }
}

function useCheckTie(settie, clickedButons, setclickedButons){
    let res=true;
    for (let i = 0; i < clickedButons.length && res; i++) {
        if (clickedButons[i] == null) {
            res=false;
        }
    }

    if (res){
        settie(true)
        setclickedButons([null, null, null, null, null, null, null, null, null])
    }
    
}