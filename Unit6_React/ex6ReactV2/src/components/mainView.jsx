import { Box, Container, Grid2, TextField, Typography } from '@mui/material';
import { useState } from 'react'
import Button from '@mui/material/Button';
import ApiRes from './apiRes';

function MainView() {

  const [dogbreedinput, setdogbreedinput] = useState("");
  const [loading, setLoading] = useState(false);
  
  const [dogbreed, setDogBreed] = useState(null);
  
  function onSubmit() {
    setDogBreed(dogbreedinput);
  }

  return (
    <>
      <Grid2 container spacing={2} columns={1} alignItems={'center'} justifyContent={'center'}>
        <Grid2 size={1}>
          <TextField 
              id='dogBreed' label="Dog Breed" variant="outlined" 
              required fullWidth size="small" 
              value={dogbreedinput} onChange={(e) => setdogbreedinput(e.target.value)}>
          </TextField>
        </Grid2>
        <Grid2 size={1}>
          <Button 
              variant="contained" 
              onClick = {onSubmit}>
            Buscar 
          </Button>
        </Grid2>
        <Grid2 size={1}>
          <ApiRes dogbreed={dogbreed}/>
        </Grid2>
      </Grid2>
    </>
  )
}

export default MainView;
