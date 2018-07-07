package de.romankoutny.kotlin

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject


val notificationQuickResponse: PublishSubject<String> = PublishSubject.create()
val notificationOrderId: PublishSubject<String> = PublishSubject.create()
val chatViewDisposable = CompositeDisposable()


fun main(args: Array<String>) {

    fun2()
}


fun fun2()
{
    Observable.concat(notificationQuickResponse, notificationOrderId)
            .subscribe { System.out.println("----==>     $it") }

    notificationOrderId.onNext("Hallo1")
    notificationQuickResponse.onNext("nQR")

}

fun fun1()
{
    notificationOrderId
            //.observeOn(AndroidSchedulers.mainThread())
            /*
            .flatMap {
                notificationModel.notifications(it)
            }
            .filter {
                (it.isNotEmpty()) and (it.first().transactions != null)
            }
            .map {
                // hier darf eh nur eine NotificationOrder zurückkommen
                it.first().transactions
            }
            .flatMap { chatModel.sendMessage(it) }
            */
            .concatWith (notificationQuickResponse)
//                .flatMap {
//                    if(it is QuickResponseMessage)
//                        quickResponseAdapter.addAll((it as QuickResponseMessage).quickResponse)
//                    messageAdapter.append(it)
//                }
            //.observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                System.out.println("==>     $it")
            }
            //.subscribe(this::receivedMessage, this::handleSendMessageError)
            //----.disposedBy(chatViewDisposable)

    notificationOrderId.onNext("Hallo1")
    notificationQuickResponse.onNext("nQR")
    notificationOrderId.onNext("Hallo2")

}

