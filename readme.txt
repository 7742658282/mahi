

- After cliking on app icon by default Michael jaksons's songs will load on recyclerview. I wrote static text key(Michael jakson)for first time
- You can search new song list with help of searchview which is top of the screen.
- If search data availabale then you will see new song list. In case of no data found you will see privous searched data in the list.
- After cliking on any listitem you will redirect to detail view with required details.


1. Retrofit API for data fetching
2. Glide library for lazy loading
3. unit testing logic written for API calling
4. Coordinator layour and cardview and toolbar used to draw view
5. MVP design patter followed


A) Model : SongResponse class , SongService(where i wrote api calling logic)

B) View : Adaper pakage and activities

c) Presenter : SongPresenter Class(a communication bridge between model and )
 