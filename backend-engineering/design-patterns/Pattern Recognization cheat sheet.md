# Pattern Recognition Cheat Sheet

| Type                              | Pain                                               | Question                                                                  | Pattern                         |
|-----------------------------------|----------------------------------------------------|---------------------------------------------------------------------------|---------------------------------|
| Find what changes                 | Will new implementations be added                  | Can I add them without changing existing code?                            | Strategy+ Factory               |
| Find if-else pain                 | Am I creating a giant if-else?                     | Will conditions grow? Am I modifying this file repeatedly?                | Strategy                        |
| Object creation pain              | Is object creation becoming ugly                   | Too many constructor parameters? Optional values?Complex setup?           | Builder                         |
| Object creation pain              | Am I creating many object types?                   | if type(csv) new csvObject()                                              | Factory                         |
| Coupling questions                | Am I tightly dependent on concrete classes?        | PaymentService ps=new RazorpayPayment();    What if tomorrow: PayPal come | Strategy + Dependency Inversion |
| Behavior questions                | Does object behavior change with state?            | Does same action behave differently?                                      | State                           |
| Notification questions            | Should one action trigger many listeners?          | Can more listeners come                                                   | Observer                        |
| Third-party integration questions | Do APIs speak different languages                  | Do I need conversion?                                                     | Adapter                         |
| Wrapping questions                | Do I want to add behavior without modifying class? | Do combinations explode?                                                  | Decorator                       |
| Instance questions                | Should only one object exist?                      |                                                                           | Singleton                       |
| Flow questions                    | Are steps fixed but some parts vary?               | Are most steps same?                                                      | Template                        |
| Command questions                 | Do actions need undo/retry/history?                |                                                                           | Command                         |



| Pattern   | Example                                                     |
|-----------|-------------------------------------------------------------|
| State     | ATM -> Card Inserted / PIN Entered / Withdrawn / No Card    |
| Observer  | Facebook -> Post -> Notify followers                        |
| Observer  | Order Placed - inventory update / email / analytics / audit |
| Adapter   | Oracle OMS response / SAP response / Custom OMS response    |
| Decorator | Coffee: -> coffee / +milk / +cream / +chocolate             |
| Template  | fetch / validate / transform / export                       |
| Command   | rollback / retry / repair transaction                       |
| Singleton | Logger / Cache / Configuration                              |