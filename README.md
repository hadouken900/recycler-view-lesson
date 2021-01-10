# RecyclerView - элемент для отображения прокручиваемого списка


> Схематично работу RecyclerView можно представить следующим образом. На экране отображаются видимые элементы списка.
Когда при прокрутке списка верхний элемент уходит за пределы экрана и становится невидимым, его содержимое очищается.
При этом сам "чистый" элемент помещается вниз экрана и заполняется новыми данными, иными словами переиспользуется, отсюда название Recycle. ( источник http://developer.alexanderklimov.ru/android/views/recyclerview-kot.php )

![a](http://developer.alexanderklimov.ru/android/views/images/recyclerview20.png)

Вся загвоздка заключается в том, что мы не можем просто отобразить данные на экран. Их сначала нужно положить в какое то View, которое сможет отображаться на экране макета.
Для этих целей существует адаптер - он связывает данные и отображение
![b](https://cdn-images-1.medium.com/fit/t/1600/480/1*T6nyso9Yu3gOCh8iJNNhLA.png)

Сам RecyclerView имеет layoutManager - объект который помогает отобразить элементы на экране. RecyclerView подключается к адаптеру, получает из него данные и передает их в layout manager, который будет решать как их отобразить.

Элементы списка в RecyclerView называются ViewHolder, за них отвечает Adapter. Именно они очищаются и заполняются каждый раз при прокрутке списка.Его нужно создать внутри класса адаптера.
У нас будет свой класс ItemViewHolder, который наследуется от ViewHolder'a. Он достаточно простой, в нём всего один элемент TextView, который мы привязываем по id.

```java
    public class ItemViewHolder extends RecyclerView.ViewHolder{

        //элемент который есть в string_item
        TextView stringTextView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            //привязываем его по id
            stringTextView = itemView.findViewById(R.id.stringTextView);
        }
    }

```
Наш адаптер будет называться ItemAdapter и будет наследоваться от обычного адаптера. У него должно быть внутреннее поле - список элементов данных, который мы будем ему передавать из MainActivity через конструктор.
```java
    ArrayList<StringItem> list;

    public ItemAdapter(ArrayList<StringItem> list) {
        this.list = list;
    }
```
Для работы адаптера нам нужно переопределить три метода:

```java
    //этот метод служит для того чтобы создавать элементы списка
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.string_item, parent, false);
        return new ItemViewHolder(view);
    }

    //этот метод нужен для того, чтобы связать ItemViewHolder и данные которые лежат в списке
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        StringItem s = list.get(position);
        holder.stringTextView.setText(s.getString());
    }

    //количество элементов в списке
    @Override
    public int getItemCount() {
        return list.size();
    }
```

В MainActivity создадим все нужные поля
```java
    //объекты которые есть на макете
    RecyclerView recyclerView;
    EditText stringEditText;

    //адаптер и лист с элементами ( пока пустой )
    ItemAdapter itemAdapter;
    ArrayList<StringItem> list = new ArrayList<>();
```

и в методе OnCreate пропишем всю оставшуюся логику

```java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //привязка объектов с макета по id
        recyclerView = findViewById(R.id.recycler);
        stringEditText = findViewById(R.id.stringEditText);

        //добавление элементов в список
        list.add(new StringItem("text 1"));
        list.add(new StringItem("text 2"));
        list.add(new StringItem("text 3"));

        //создание адаптера, записываем туда список с данными
        itemAdapter = new ItemAdapter(list);
        
        //привязываем адаптер к recyclerView
        recyclerView.setAdapter(itemAdapter);
        
        //вертикальный список
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        //добавление разделителей между элементами
        recyclerView.addItemDecoration(new DividerItemDecoration(this,1));
    }
```

Если данные в коде будут меняться, нужно оповестить адаптер об этом
```java
itemAdapter.notifyDataSetChanged();
```

Для того чтобы элементы списка можно было свайпать нужно добавить ItemTouchHelper и привязать его к RecyclerView
```java

ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }


            //прописать нужную логику при свайпе элемента списка сюда
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            }
        });
        
        //привязка к RecyclerView
        itemTouchHelper.attachToRecyclerView(recyclerView);
```

к элементу списка можно так же добавить OnClickListener. делать это нужно внутри конструктора ItemViewHolder

```java
itemView.setOnClickListener(...);
```

# Подготовка
- скопировать файлы из этого репозитория в ваш проект (файлы java в папку java/ваша_папка/ , файлы xml в res/layout/)
- поменять строчку package в каждом java файле

# Задания
- 1 - на главном макете есть поле для ввода и кнопка. нужно сделать так чтобы при нажатии кнопки строчка из поля добавлялась в список.
- 2 - сделать так, чтобы при свайпе элемента он удалялся
- 3 - изменить макет string_item сделать в виде карточки (cardView)
- 4 - изменить макет string_item добавить ещё одно текстовое поле 
- 5 - *сделать так, чтобы при нажатии на элемент, отображался его номер в списке
- 6 - *при нажатии на элемент, открывалась новая отдельная активность с данными, на которые нажали




