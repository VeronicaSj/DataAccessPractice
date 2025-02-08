import * as React from 'react';

import Grid from '@mui/material/Grid2';
import Paper from '@mui/material/Paper';

import Card from './myCard';


function Content() {

    return (
            <Grid container>
                <Grid>
                    Es mejor agrupar las distintas zonas
                    en componentes React para luego usarlos 
                    dentro del App.tsx
                    Crear 3 componentes principales es recomendable:
                    miBar.tsx   miContent.tsx   miCard.tsx
                    Utilizar los  componentes en la App
                    Con el Grid utilizar props que lo hagan responsivo
                    Con Button y otros componentes usar sus props para
                    dejarlos a vuestro gusto. Utilizar los siguientes
                    componentes de MUI en este orden/jerarquía:
                </Grid>
                <Grid >
                    <Card></Card>
                </Grid>
            </Grid>
    );

}

export default Content;