import javax.swing.*;
import java.lang.reflect.Array;
import java.util.*;

public class ManagerCursuri{
    private ArrayList<Curs> cursuri;

    public Curs search(Curs unCurs) throws Exception {
        int i = cursuri.indexOf(unCurs);
        if ( i != -1 ) {
            return cursuri.get(i);
        }
        else {
            throw new Exception("Cursul " + unCurs + " nu se se regaseste in programa scolara");
        }
    }
    //Get-er
    public ArrayList<Curs> getCursuri() {
        return this.cursuri;
    }

    public ArrayList<String> ShowCoursesOfTeacher(Profesor profesor)
    {
        int counter = 1;
        ArrayList<String> list = new ArrayList<String>();
        for(Curs c : cursuri)
        {
            if(c.prof.equals(profesor))
            {
                System.out.println(counter + ". " + c.getNume());
                list.add(c.getNume());
                counter++;
            }
        }
        System.out.println(counter + ". " + "Exit Program");
        return list;
    }

    public Curs[] getCursuriArray() {
        Curs[] c = new Curs[cursuri.size()];
        c = cursuri.toArray(c);
        return c;
    }


    public String getStudentYear(Student student)
    {
        for(Curs curs : cursuri)
        {
            if(curs.SearchStudent(student))
            {
                Student[] students = curs.studenti.toArray(new Student[curs.studenti.size()]);
                ArrayList<Student> s = new ArrayList<>(Arrays.asList(students));
                String year = s.get(s.indexOf(student)).an.toString();
                return year;
            }
        }
        return null;
    }

    public String[] SerchStudentInCours(Student student)
    {
        String[] line = new String[cursuri.size()];
        for (Curs c : cursuri)
        {
            if(c.SearchStudent(student))
            {
                String restanta = null;
                if(c.note.get(student) == null)
                {
                    restanta = "";
                }
                else
                    if (c.note.get(student) == 0)
                    {
                        restanta = c.note.get(student).toString();
                    }
                    else
                        if(c.note.get(student) < 5)
                        {
                            restanta = c.note.get(student).toString() + " Restanta";
                        }
                        else
                            if(c.note.get(student) >= 5)
                            {
                                restanta = c.note.get(student).toString();
                            }
                line[cursuri.indexOf(c)] = c.nume + ": " + restanta;
            }
        }
        return line;
    }

    //Constructor
    public ManagerCursuri(ArrayList<Curs> cursuri) {
        this.cursuri = cursuri;
    }

    public ManagerCursuri() { }

    public ManagerCursuri(Curs[] c)
    {
       this.cursuri = new ArrayList<>(Arrays.asList(c));
    }
    public void setCurs(Curs[] c)
    {
        this.cursuri = new ArrayList<>(Arrays.asList(c));
    }

    //Functie de adaugare curs
    public void AddCurs(Curs curs) {
        this.cursuri.add(curs);
    }

    //Afiseaza notele date de un profesor
    public void ShowNoteDeUnProfesor(Profesor p)
    {
        int count = 0;
        float medie = 0;
        for(Curs c : this.cursuri)
        {
            if(c.getProf().equals(p))
            {
                medie += c.mediaStudenti();
                count++;
            }
        }
        if(count == 0)
        {
            System.out.println("Numele introdus nu este corect!");
            System.exit(0);
        }
        float average = count == 0 ? 0 : medie/(float) count;
        System.out.println("Media notelor data de profesorul " + p.getNume() + p.getPrenume() + " este: " + average);
    }

    //Afiseaza notele de la toate cursurile pentru toti studentii inscrisi la fiecare curs
    public void ShowNotes() {
        for (Curs c : this.cursuri) {
            System.out.println("Curs: " + c.getNume());
            for (Student s : c.getStudenti()) {
                String gradeAsString = c.getNote().get(s) != null ? c.getNote().get(s).toString() : " nu a fost notat";
                System.out.println("Studentul: " + s.getNume() + " " + s.getPrenume() + " are nota: " + gradeAsString);
            }
        }
    }
    //Face media la un anumit curs specificat ca si parametru al functiei
    public void MedieCurs(Curs curs)
    {
        Curs c;
        try
        {
            c = this.search(curs);
            System.out.println("Media studentilor la cursul: " + c.getNume() + " este: " + c.mediaStudenti());
        }
        catch (Exception e)
        {
            System.out.println("Cursul nu a fost gasit!");
        }
    }

    public float MediaStudent(Student student)
    {
        float mean = 0;
        int nr = 0;
        boolean ok = true;
        for(Curs curs : cursuri)
        {
            if(curs.SearchStudent(student))
            {
                mean += curs.note.get(student) != null ? curs.note.get(student) : 0;
                if(curs.note.get(student) == null)
                {
                    ok = false;
                    break;
                }
                nr++;
            }
        }
        if(ok == false)
        {
            return 0;
        }
        if(mean == 0)
            return 0;
        else
            return mean/nr;
    }

    public void EditCurs(Curs c, Curs cursNou) throws Exception {
        // Caut cursul in lista de cursuri
        int i = this.cursuri.indexOf(c);
        // verific daca cursul exista
        if ( i != -1) {
            this.cursuri.set(i, cursNou);
        } else {
            throw new Exception("Cursul " + c + " nu se regaseste in programa scolara");
        }
    }

    @Override
    public String toString() {
        return "ManagerCursuri{" +
                "cursuri=" + this.cursuri +
                '}';
    }

    public void RemoveCurs(Curs curs) throws Exception {
        if (!this.cursuri.remove(curs)) {
            throw new Exception("Cursrul " + curs.getNume() + " nu poate fi sters pentru ca nu se regaseste in programa scolara");
        }
    }

    public void reportAllCourses() {
        for( Curs c: cursuri) {
            System.out.println( c.getNume() + " " + c.getDescriere());
            try {
                // this.reportStudentsOf(c);
            } catch (Exception e) {

            }
        }
    }

    //Afiseaza stundetii de la un anumit curs specificat ca parametru
    public ArrayList<String> StudentiCurs(Curs curs) {
        int index = -1, pos = 0, ok = 0;
        for (Curs c : cursuri) {
            index++;
            if (c.compareTo(curs) == 0) {
                pos = index;
                ok = 1;
            }
        }

        if (ok == 0) {
            System.out.println("Numele cursului nu exista!");
            System.exit(0);
        }

        return cursuri.get(pos).ShowStudents();
    }
    //Sorteaza cursurile dupa nume
    public void SorteazaDupaNume()
    {
        this.cursuri.sort(Curs::compareTo);
    }
    //Sorteaza cursurile dupa numele profesorilor
    public void  SorteazaDupaProf()
    {
        this.cursuri.sort(Curs::compareToProf);
    }
    //Sorteaza cursurile dupa numarul de studentii la curs
    public void  SorteazaDupaNrStudenti()
    {
        this.cursuri.sort(Curs::compareToNrStudenti);
    }
}