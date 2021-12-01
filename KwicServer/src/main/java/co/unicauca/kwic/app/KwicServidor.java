
/*!
\file   CKwicServidor.java
\date   2021-10-25
\author Julián Martinez <juliansmartinez@unicauca.edu.co> 104618021321
\brief  Clase Cliente del programa servidor.
\par Copyright
Information contained herein is proprietary to and constitutes valuable
confidential trade secrets of unicauca, and
is subject to restrictions on use and disclosure.
\par
Copyright (c) unicauca 2021. All rights reserved.
\par
The copyright notices above do not evidence any actual or
intended publication of this material.
*******************************************************************************/
package co.unicauca.kwic.app;

import co.unica.kwic.infraestructura.KwicServerSocket;

public class KwicServidor {

    public static void main(String[] args) {
        /*Inicia el Servidor*/
        KwicServerSocket s = new KwicServerSocket();
        s.start();
    }

}
