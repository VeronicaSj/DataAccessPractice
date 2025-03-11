import { Grid2, Container, TextField, Typography } from '@mui/material';
import React from 'react';
import { useState } from 'react'
import ConsultWeather from './weatherConsultant';
import Button from '@mui/material/Button';
import WeatherCard from './weatherCard';


function MainView() {

  const [city, setCity] = useState("");
  const [loading, setLoading] = useState(false);
  const [weatherCard, setWeatherCard] = useState(null);

  function onSubmit() {
    setLoading(true)
    console.log('setLoading(true)')
    setWeatherCard(<ConsultWeather city={city} /> );
  }

  if(weatherCard != null && loading){
    setLoading(false)
    console.log('setLoading(false)')
  }

  return (
      <Grid2 container spacing={2} columns={1} alignItems={'center'} justifyContent={'center'}>
        <Grid2 size={1}>
          <TextField 
              id='city' label="Ciudad" variant="outlined" 
              required fullWidth size="small" value={city} 
              onChange={(e) => setCity(e.target.value)}></TextField>
        </Grid2>
        <Grid2 size={1}>
          <Button 
              variant="contained" 
              loading={loading}
              loadingIndicator="Cargando..."
              onClick={onSubmit}> 
            Buscar 
          </Button>
        </Grid2>
        <Grid2 size={1}>
            {weatherCard}
        </Grid2>
      </Grid2>
  )
}

export default MainView;
