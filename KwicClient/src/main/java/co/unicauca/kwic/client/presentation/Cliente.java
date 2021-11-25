
/*!
\file   Cliente.java
\date   2021-10-25
\author Julián Martinez <juliansmartinez@unicauca.edu.co> 104618021321
\brief  Clase Cliente del programa del lado del Cliente.
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
package co.unicauca.kwic.client.presentation;

import co.unicauca.kwic.client.access.KwicAccessImplSockets;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Julián
 */
public class Cliente {

    public static void main(String[] args) throws Exception {
        KwicAccessImplSockets request = new KwicAccessImplSockets();
        List<String> listaPalabras = new ArrayList<>();
        listaPalabras.add("Primer Parcial");
        listaPalabras.add("Principio de Liskov");
        listaPalabras.add("Inversión de Dependencias");
        System.out.println(request.generateKwic(listaPalabras).getResponse().toString());

    }

}
