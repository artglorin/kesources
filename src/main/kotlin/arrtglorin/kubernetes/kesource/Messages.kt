package arrtglorin.kubernetes.kesource

import io.kvision.i18n.gettext
import io.kvision.i18n.tr

fun String.tr() = tr(this)

object Messages {
    const val resourcesList = "Список ресурсов"

    object Components {
        object ResourceSaveModal {
            const val title = "Введите метку"
            const val description = "Введите короткое описания, не больше 20 символов, для " +
                    "удобного поиска ресурса в дальнейшем. "
            const val declineTooltip = "Отменить сохранение ресурса."
            const val declineText = "Отменить"
            const val confirmTooltip = "Подтвердить сохранение ресурса."
            const val confirmText = "Сохранить"
        }
    }

    object Pages {
        object Welcome {
            val title = tr("Приложение с описанием ресурсов Kubernetes")
            var description = tr("""
                Это приложение помогает разобраться в ресурсах Kubernetes. 
                Не только какие ресурсы существуют, какие параметры за них отвечают, но так-же
                 легко создавать ресурсы через web формы.
                 Для начала выберите ресурс из "Списка ресурсов" слева.
            """.trimIndent())
        }
        object Deployment {
            val title = "Deployment"
            val description = gettext("""
                |Deployment – это базовая сущность для разворачивания контейнера внутри приложения.
                |<br/>
                |Deployment привязан к <a href='#%1'>%2</a>
                |""".trimMargin(),
                Routs.Resources.namespace,
                K8sResource.namespace
            )
        }
        object Namespace {

            val title = "Namespace"
            val description = gettext("""
                |<p>
                |Kubernetes поддерживает несколько виртуальных кластеров в одном физическом кластере. 
                |Такие виртуальные кластеры называются пространствами имён.
                |</p>
                |<p>
                |Подробнее документацию можно почитать на <a href="https://kubernetes
                |.io/ru/docs/concepts/overview/working-with-objects/namespaces/" 
                |target="_blank">здесь</a>.
                |</p>
                |""".trimMargin(),
                Routs.Resources.namespace,
                K8sResource.namespace
            )
        }
    }

    object K8sResource {
        val service =  "Service"
        val namespace = "Namespace"
        val deployment = "Deployment"
        val serviceAccount = "ServiceAccount"
    }
}

