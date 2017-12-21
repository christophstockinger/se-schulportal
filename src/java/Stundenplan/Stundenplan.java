/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stundenplan;

import Modul_example.*;

/**
 *
 * @author Christoph
 */
public class Stundenplan {
    
    public static String modulname = "Stundenplan";
    public static String moduldesc = "Hier könnte eventuell noch ein Text zur Modulerklärung stehen. <br>Muss aber nicht sein"; // Zeilenumbruch mit <br>-Tag erzeugen, da es in einen <p>-Tag gerendert wird.
    
    public Stundenplan() {}
    
    public static String stundenplan() {
        String output = "<ul>";
        output += "<li>  <a href='#'>Menüpunkt 1</a> </li>";
        output += "<li> <a href='#'>Menüpunkt 2</a> </li>";
        output += "</ul>";
        output +=               "<div id='main_container'>";
        output +=               "<!-- tables inside this DIV could have draggable content -->";
        output +=                   "<div id='redips-drag'>";
        output +=                   "<!-- left container (table with subjects) -->";
        output +=                       "<div id='left'>";
        output +=                           "<table id='table1'>";
        output +=                           "<colgroup>";
        output +=                           "<col width='220'/>";
        output +=                           "</colgroup>";
        output +=                           "<tbody>";
        output +=                               "<tr><td class='dark'><div id='ar' class='redips-drag redips-clone ar'>Arts</div><input id='b_ar' class='ar' type='button' value='' onclick='redips.report('ar')' title='Show only Arts'/></td></tr>";
        output +=                               "<tr><td class='dark'><div id='bi' class='redips-drag redips-clone bi'>Biology</div><input id='b_bi' class='bi' type='button' value='' onclick='redips.report('bi')' title='Show only Biology'/></td></tr>";
        output +=                               "<tr><td class='dark'><div id='ch' class='redips-drag redips-clone ch'>Chemistry</div><input id='b_ch' class='ch' type='button' value='' onclick='redips.report('ch')' title='Show only Chemistry'/></td></tr>";
        output +=                               "<tr><td class='dark'><div id='en' class='redips-drag redips-clone en'>English</div><input id='b_en' class='en' type='button' value='' onclick='redips.report('en')' title='Show only English'/></td></tr>";
        output +=                               "<tr><td class='dark'><div id='et' class='redips-drag redips-clone et'>Ethics</div><input id='b_et' class='et' type='button' value='' onclick='redips.report('et')' title='Show only Ethics'/></td></tr>";
        output +=                               "<tr><td class='dark'><div id='hi' class='redips-drag redips-clone hi'>History</div><input id='b_hi' class='hi' type='button' value='' onclick='redips.report('hi')' title='Show only History'/></td></tr>";
        output +=                               "<tr><td class='dark'><div id='it' class='redips-drag redips-clone it'>IT</div><input id='b_it' class='it' type='button' value='' onclick='redips.report('it')' title='Show only IT'/></td></tr>";
        output +=                               "<tr><td class='dark'><div id='ma' class='redips-drag redips-clone ma'>Mathematics</div><input id='b_ma' class='ma' type='button' value='' onclick='redips.report('ma')' title='Show only Mathematics'/></td></tr>";
        output +=                               "<tr><td class='dark'><div id='ph' class='redips-drag redips-clone ph'>Physics</div><input id='b_ph' class='ph' type='button' value='' onclick='redips.report('ph')' title='Show only Physics'/></td></tr>";
        output +=                               "<tr><td class='redips-trash' title='Trash'>Trash</td></tr>";
        output +=                           "</tbody>";
        output +=                           "</table>";
        output +=                      "</div><!-- left container -->";
	output +="<div id='right'> ";
	output +="<table id='table2'>";
	output +=					"<colgroup>";
	output +=						"<col width='50'/>";
	output +=						"<col width='100'/>";
	output +=						"<col width='100'/>";
	output +=						"<col width='100'/>";
	output +=						"<col width='100'/>";
	output +=						"<col width='100'/>";
	output +=					"</colgroup>";
	output +=					"<tbody>";
	output +=						"<tr>";
	output +=							"<td class='redips-mark blank'>";
	output +=								"<input id='week' type='checkbox' title='Apply school subjects to the week' checked/>";
	output +=								"<input id='report' type='checkbox' title='Show subject report'/>";
	output +=							"</td>";
	output +=							"<td class='redips-mark dark'>Montag</td>";
	output +=							"<td class='redips-mark dark'>Dienstag</td>";
	output +=							"<td class='redips-mark dark'>Mittwoch</td>";
	output +=							"<td class='redips-mark dark'>Donnerstag</td>";
	output +=							"<td class='redips-mark dark'>Freitag</td>";

	output +=						"</tr>";
	output +=						"<tr>";
	output +=							"<td class='redips-mark dark'>8:00</td>";
	output +=							"<td></td>";
	output +=							"<td></td>";
	output +=							"<td></td>";
	output +=							"<td></td>";
	output +=						"<td></td>";
	output +=					"</tr>";
	output +=					"<tr>";
	output +=						"<td class='redips-mark dark'>9:00</td>";
	output +=						"<td></td>";
	output +=						"<td></td>";
	output +=						"<td></td>";
	output +=						"<td></td>";
	output +=						"<td></td>";
	output +=					"</tr>";
	output +=					"<tr>";
	output +=						"<td class='redips-mark dark'>10:00</td>";
	output +=						"<td></td>";
	output +=						"<td></td>";
	output +=						"<td></td>";
	output +=						"<td></td>";
	output +=						"<td></td>";
	output +=					"</tr>";
	output +=					"<tr>";
	output +=						"<td class='redips-mark dark'>11:00</td>";
	output +=						"<td></td>";
	output +=						"<td></td>";
	output +=						"<td></td>";
	output +=						"<td></td>";
	output +=						"<td></td>";
	output +=					"</tr>";
	output +=					"<tr>";
	output +=						"<td class='redips-mark dark'>12:00</td>";
	output +=						"<td></td>";
	output +=						"<td></td>";
	output +=						"<td></td>";
	output +=						"<td></td>";
	output +=						"<td></td>";
	output +=					"</tr>";
	output +=					"<tr>";
        output +=					"<td class='redips-mark dark'>13:00</td>";
	output +=					"<td class='redips-mark lunch' colspan='5'>Mittag</td>";
	output +=				"</tr>";
	output +=				"<tr>";
	output +=					"<td class='redips-mark dark'>14:00</td>";
	output +=					"<td></td>";
	output +=					"<td></td>";
	output +=					"<td></td>";
	output +=					"<td></td>";
	output +=					"<td></td>";
	output +=				"</tr>";
	output +=				"<tr>";
	output +=					"<td class='redips-mark dark'>15:00</td>";
	output +=					"<td></td>";
	output +=					"<td></td>";
	output +=					"<td></td>";
	output +=					"<td></td>";
	output +=					"<td></td>";
	output +=				"</tr>";
	output +=				"<tr>";
	output +=					"<td class='redips-mark dark'>16:00</td>";
	output +=					"<td></td>";
	output +=					"<td></td>";
	output +=					"<td></td>";
	output +=					"<td></td>";
	output +=					"<td></td>";
	output +=				"</tr>";
	output +=			"</tbody>";
	output +=		"</table>";
	output +=	"</div><!-- right container -->";
	output +=	"</div><!-- drag container -->";
	output +=	"<div id='message'>Drag school subjects to the timetable (clone subjects with SHIFT key)</div>";
        output +=       "</div><!-- main container -->";
        return output;
    }
    
    
    
    
}
